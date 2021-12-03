package com.gymmanage.sys.dao;

import com.gymmanage.sys.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    List<Role> roleList();

}
