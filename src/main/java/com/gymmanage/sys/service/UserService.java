package com.gymmanage.sys.service;

import com.gymmanage.client.entity.Client;
import com.gymmanage.sys.entity.User;
import com.gymmanage.utils.AjaxRes;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {
    List<User> selectAll();

    AjaxRes addUser(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException;

    AjaxRes updateUser(User user);

    void deleteUser(Integer id);

    boolean checkPwd(String username, String pwd );

    boolean checkPwd2(String username, String pwd );

    String checkClientPwd(String username, String pwd, HttpSession session);

    Client getClientByUsername(String username);

    User getOne(Integer id);

    AjaxRes passwordUpdate(String username, String pwd) throws NoSuchAlgorithmException, UnsupportedEncodingException;

    AjaxRes passwordUpdate2(String username, String pwd);

    List<User> freeUser();

    void signIn(String name,String phone,String pwd1);

    int getByPhone(String phone);
}
