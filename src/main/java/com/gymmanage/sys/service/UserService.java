package com.gymmanage.sys.service;

import com.gymmanage.sys.entity.User;

import java.util.List;

public interface UserService {
    List<User> selectAll();

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(Integer id);

    boolean checkPwd(String username, String pwd);
}
