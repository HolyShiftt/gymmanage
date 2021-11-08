package com.gymmanage.sys.service;

import com.gymmanage.sys.entity.User;

import java.util.List;

public interface UserService {
    public List<User> selectAll();

    public void addUser(User user);

    void updateUser(User user);

    void deleteUser(Integer id);

    boolean checkPwd(String username, String pwd);
}
