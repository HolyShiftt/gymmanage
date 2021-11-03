package com.gymmanage.sys.controller;

import com.gymmanage.sys.entity.User;
import com.gymmanage.sys.service.UserService;
import com.gymmanage.utils.AjaxRes;
import com.gymmanage.utils.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/userPage")
    public String user(){
        return "/index";
    }

    @RequestMapping("/userList")
    @ResponseBody
    public Table userList(){
        int length = userService.selectAll().size();
        return Table.success(Long.valueOf(length),userService.selectAll());
    }

    @RequestMapping("/userAdd")
    @ResponseBody
    public AjaxRes userAdd(User user){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            userService.addUser(user);
            ajaxRes.setMsg("保存成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("保存失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @RequestMapping("/userUpdate")
    @ResponseBody
    public AjaxRes userUpdate(User user){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            userService.updateUser(user);
            ajaxRes.setMsg("更新成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("更新失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @RequestMapping("/userDelete")
    @ResponseBody
    public AjaxRes userDelete(Integer id){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            userService.deleteUser(id);
            ajaxRes.setMsg("删除成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("删除失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }
}
