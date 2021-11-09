package com.gymmanage.sys.dao;

import com.gymmanage.sys.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    String getPwd(String username);
}
