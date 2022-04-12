package com.gymmanage.sys.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gymmanage.sys.entity.User;
import com.gymmanage.sys.service.UserService;
import com.gymmanage.utils.AjaxRes;
import com.gymmanage.utils.LayuiPage;
import com.gymmanage.utils.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/userPage")
    public String user(){
        return "/sys/user";
    }

    @RequestMapping("/userAddPage")
    public String userAdd(){
        return "/sys/userAdd";
    }

    @RequestMapping("/updPwd")
    public String updPwd(){
        return "/sys/updPwd";
    }

    @RequestMapping("/userUpdatePage")
    public String userUpdate(Integer id, HttpSession session){
        session.setAttribute("updateUserId",id);
        return "/sys/userUpdate";
    }

    // 用户登录检查
    @RequestMapping("/userCheck")
    @ResponseBody
    public AjaxRes userCheck(String username, String pwd, HttpSession session,String role) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        AjaxRes ajaxRes = new AjaxRes();
        // 密码加密
        MessageDigest md5= MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        boolean res = false;
        if (role.equals("admin")){
            res = userService.checkPwd(username, base64en.encode(md5.digest(pwd.getBytes("utf-8"))));
        }else if (role.equals("user")) {
            String s = userService.checkClientPwd(username, pwd, session);
            if(s.equals("no")){
                res=false;
            }else {
                username = s;
                res = true;
            }
        }
        ajaxRes.setSuccess(res);
        if (res){
            ajaxRes.setMsg("登录成功");
            session.setAttribute("username",username);
        }else {
            ajaxRes.setMsg("登录失败");
        }
        return ajaxRes;
    }

    // 获取用户信息
    @RequestMapping("/getUser")
    @ResponseBody
    public String gete(HttpSession session){
        return session.getAttribute("username").toString();
    }

    // 用户注销
    @RequestMapping("/logout")
    @ResponseBody
    public AjaxRes logout(HttpSession session){
        AjaxRes ajaxRes = new AjaxRes();
        if (session != null) {
            session.invalidate();//调用session的invalidate()方法，将保存的session删除
        }
        ajaxRes.setSuccess(true);
        ajaxRes.setMsg("退出登录成功！");
        return ajaxRes;
    }

    @RequestMapping("/userList")
    @ResponseBody
    public Table userList(LayuiPage layuiPage){
        Page<?> page = PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());
        List<User> users = userService.selectAll();
        return Table.success(Long.valueOf(page.getTotal()),users);
    }

    @RequestMapping("/userAdd")
    @ResponseBody
    public AjaxRes userAdd(User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return userService.addUser(user);
    }

    @RequestMapping("/userUpdate")
    @ResponseBody
    public AjaxRes userUpdate(User user){
        return userService.updateUser(user);
    }

    @RequestMapping("/userDelete")
    @ResponseBody
    public AjaxRes userDelete(Integer id){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            userService.deleteUser(id);
            ajaxRes.setMsg("删除成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("删除失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @RequestMapping("/getOne")
    @ResponseBody
    public User getOne(HttpSession session){
        return userService.getOne((Integer)session.getAttribute("updateUserId"));
    }

    @RequestMapping("/passwordUpdate")
    @ResponseBody
    public AjaxRes passwordUpdate(String pwd1,String pwd2,HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        AjaxRes ajaxRes = new AjaxRes();
        String username = (String) session.getAttribute("username");
        MessageDigest md5= MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        boolean res = userService.checkPwd(username, base64en.encode(md5.digest(pwd1.getBytes("utf-8"))));
        if (!res){
            ajaxRes.setMsg("原密码错误");
            ajaxRes.setSuccess(false);
        }else{
            ajaxRes = userService.passwordUpdate(username, pwd2);
        }
        return ajaxRes;
    }

    @RequestMapping("/passwordUpdate2")
    @ResponseBody
    public AjaxRes passwordUpdate2(String pwd1,String pwd2,HttpSession session){
        AjaxRes ajaxRes = new AjaxRes();
        String username = (String) session.getAttribute("username");
        boolean res = userService.checkPwd2(username, pwd1);
        if (!res){
            ajaxRes.setMsg("原密码错误");
            ajaxRes.setSuccess(false);
        }else{
            ajaxRes = userService.passwordUpdate2(username, pwd2);
        }
        return ajaxRes;
    }

    @RequestMapping("/freeUser")
    @ResponseBody
    public List<User> freeUser(){
        return userService.freeUser();
    }

    @RequestMapping("/signIn")
    @ResponseBody
    public AjaxRes signIn(String name,String phone,String pwd1){
        AjaxRes ajaxRes = new AjaxRes();
        int byPhone = userService.getByPhone(phone);
        if (byPhone == 0){
            userService.signIn(name,phone,pwd1);
            ajaxRes.setSuccess(true);
        }else {
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

}
