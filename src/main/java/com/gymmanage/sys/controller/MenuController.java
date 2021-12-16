package com.gymmanage.sys.controller;

import com.gymmanage.sys.entity.Menu;
import com.gymmanage.sys.service.MenuService;
import com.gymmanage.utils.AjaxRes;
import com.gymmanage.utils.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/menuPage")
    public String menuPage(){
        return "/sys/menu";
    }

    @RequestMapping("/menuList")
    @ResponseBody
    public Table menuList(Integer pid){
        List<Menu> menus = menuService.selectAll(pid);
        int length = menus.size();
        return Table.success(Long.valueOf(length),menus);
    }

    @RequestMapping("/menuTree")
    @ResponseBody
    public Table menuTree(){
        return Table.success((long)0, menuService.getMenuTree());
    }

    @RequestMapping("/addMenu")
    @ResponseBody
    public AjaxRes addMenu(String title, String url, Integer parentId){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            menuService.addMenu(title, url, parentId);
            ajaxRes.setMsg("保存成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("保存失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @RequestMapping("/deleteMenu")
    @ResponseBody
    public AjaxRes deleteMenu(Integer id){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            menuService.deleteMenu(id);
            ajaxRes.setMsg("删除成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("删除失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @RequestMapping("/updateMenu")
    @ResponseBody
    public AjaxRes updateMenu(Integer id, String title, String url, Integer parentId){
        AjaxRes ajaxRes = new AjaxRes();
        List<Integer> childrenId = menuService.getChildrenId(id);
        for (Integer children : childrenId) {
            if (children.equals(parentId)){
                ajaxRes.setMsg("不能将自己的子菜单作为父级菜单");
                ajaxRes.setSuccess(false);
                return ajaxRes;
            }
        }
        try {
            menuService.updateMenu(id, title, url, parentId);
            ajaxRes.setMsg("更新成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            System.out.println(e);
            ajaxRes.setMsg("更新失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

}
