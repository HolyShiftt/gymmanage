package com.gymmanage.sys.service.Impl;

import com.gymmanage.sys.dao.RoleMapper;
import com.gymmanage.sys.entity.Role;
import com.gymmanage.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> roleList() {
        return roleMapper.roleList();
    }
}
