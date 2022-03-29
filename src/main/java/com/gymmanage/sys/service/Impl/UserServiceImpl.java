package com.gymmanage.sys.service.Impl;

import com.gymmanage.client.entity.Client;
import com.gymmanage.sys.dao.UserMapper;
import com.gymmanage.sys.entity.User;
import com.gymmanage.sys.service.UserService;
import com.gymmanage.utils.AjaxRes;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public AjaxRes addUser(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        AjaxRes ajaxRes = new AjaxRes();
        if (userMapper.checkUsername(user.getUsername()) != null){
            ajaxRes.setMsg("该用户名已存在");
            ajaxRes.setSuccess(false);
        }else{
            if (user.getPwd().equals("") || user.getPwd()==null){
                user.setPwd("654321");
            }
            // 密码加密
            MessageDigest md5= MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            user.setPwd(base64en.encode(md5.digest(user.getPwd().getBytes("utf-8"))));
            try {
                userMapper.addUser(user);
                ajaxRes.setMsg("保存成功");
                ajaxRes.setSuccess(true);
            }catch (Exception e){
                ajaxRes.setMsg("保存失败");
                ajaxRes.setSuccess(false);
            }
        }
        return ajaxRes;
    }

    @Override
    public AjaxRes updateUser(User user) {
        AjaxRes ajaxRes = new AjaxRes();
        String admin = userMapper.checkRole(user.getRole(),user.getId());
        if(admin==null){
            userMapper.updateUser(user);
            ajaxRes.setMsg("更新成功");
            ajaxRes.setSuccess(true);
            return ajaxRes;
        }else{
            ajaxRes.setMsg("该场地管理员已经由 '"+admin+"' 担任,请重新选择职位");
            ajaxRes.setSuccess(false);
            return ajaxRes;
        }

    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }

    @Override
    public boolean checkPwd(String username, String pwd) {
        String pwd1 = userMapper.getPwd(username);
        return pwd.equals(pwd1);
    }

    @Override
    public boolean checkPwd2(String username, String pwd) {
        String pwd1 = userMapper.getClientPwd(username).getPwd();
        return pwd.equals(pwd1);
    }

    @Override
    public String checkClientPwd(String username, String pwd, HttpSession session) {
        Client clientPwd = userMapper.getClientPwd(username);
        if (clientPwd!=null){
            String pwd1 = clientPwd.getPwd();
            if (pwd.equals(pwd1)){
                session.setAttribute("userId",clientPwd.getId());
                return clientPwd.getUser_name();
            }else{
                return "no";
            }
        }else {
            return "no";
        }
    }

    @Override
    public Client getClientByUsername(String username) {
        return userMapper.getClientPwd(username);
    }

    @Override
    public User getOne(Integer id) {
        return userMapper.getOne(id);
    }

    @Override
    public AjaxRes passwordUpdate(String username, String pwd) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        AjaxRes ajaxRes = new AjaxRes();
        // 密码加密
        MessageDigest md5= MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        String encode = base64en.encode(md5.digest(pwd.getBytes("utf-8")));
        try {
            userMapper.passwordUpdate(username,encode);
            ajaxRes.setMsg("密码修改成功,请重新登录");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("密码修改失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public AjaxRes passwordUpdate2(String username, String pwd) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            userMapper.passwordUpdate2(username,pwd);
            ajaxRes.setMsg("密码修改成功,请重新登录");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            System.out.println(e);
            ajaxRes.setMsg("密码修改失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public List<User> freeUser() {
        return userMapper.freeUser();
    }
}
