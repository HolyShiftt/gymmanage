package com.gymmanage.sys.dao;

import com.gymmanage.sys.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    String getPwd(String username);

    List<User> selectAll();

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(Integer id);

    String checkUsername(String username);

    User getOne(Integer id);

    // 一个管理只能由一个人来担任，判断是否存在该管理
    String checkRole(String roleId,Integer id);
}
