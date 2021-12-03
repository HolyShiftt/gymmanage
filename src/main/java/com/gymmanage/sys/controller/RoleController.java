package com.gymmanage.sys.controller;

import com.gymmanage.sys.entity.Role;
import com.gymmanage.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/roleList")
    @ResponseBody
    public List<Role> roleList(){
        return roleService.roleList();
    }

}
