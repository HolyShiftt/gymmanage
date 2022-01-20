package com.gymmanage.sys.service;

import com.gymmanage.sys.entity.User;
import com.gymmanage.utils.AjaxRes;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {
    List<User> selectAll();

    AjaxRes addUser(User user) throws NoSuchAlgorithmException, UnsupportedEncodingException;

    AjaxRes updateUser(User user);

    void deleteUser(Integer id);

    boolean checkPwd(String username, String pwd);

    User getOne(Integer id);

    AjaxRes passwordUpdate(String username, String pwd) throws NoSuchAlgorithmException, UnsupportedEncodingException;

    List<User> freeUser();
}
