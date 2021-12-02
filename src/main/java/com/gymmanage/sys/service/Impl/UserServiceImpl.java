package com.gymmanage.sys.service.Impl;

import com.gymmanage.sys.dao.UserMapper;
import com.gymmanage.sys.entity.User;
import com.gymmanage.sys.service.UserService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(Integer id) {

    }

    @Override
    public boolean checkPwd(String username, String pwd) {
        String pwd1 = userMapper.getPwd(username);
        if (pwd.equals(pwd1)){
            return true;
        }else{
            return false;
        }
    }
}
