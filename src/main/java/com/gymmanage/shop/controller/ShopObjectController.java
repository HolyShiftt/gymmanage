package com.gymmanage.shop.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gymmanage.shop.entity.ShopObject;
import com.gymmanage.shop.service.ShopObjectService;
import com.gymmanage.utils.AjaxRes;
import com.gymmanage.utils.LayuiPage;
import com.gymmanage.utils.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("shopObject")
public class ShopObjectController {
    @Autowired
    private ShopObjectService shopObjectService;

    @RequestMapping("shopObjectPage")
    public String shopObjectPage(){
        return "/shop/shopObject";
    }

    @RequestMapping("shopObjectAddPage")
    public String shopObjectAddPage(){
        return "/shop/shopObjectAdd";
    }

    @RequestMapping("shopObjectUpdatePage")
    public String shopObjectUpdatePage(Integer id, HttpSession session){
        session.setAttribute("updateShopObjectId",id);
        return "/shop/shopObjectUpdate";
    }

    @RequestMapping("/shopObjectList")
    @ResponseBody
    public Table shopObjectList(LayuiPage layuiPage){
        Page<?> page = PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());
        List<ShopObject> shopObject = shopObjectService.selectAll();
        return Table.success(Long.valueOf(page.getTotal()),shopObject);
    }

    @RequestMapping("/shopObjectAdd")
    @ResponseBody
    public AjaxRes shopObjectAdd(ShopObject shopObject){
        return shopObjectService.shopObjectAdd(shopObject);
    }

    @RequestMapping("/getOne")
    @ResponseBody
    public ShopObject getOne(HttpSession session){
        return shopObjectService.getOne((Integer)session.getAttribute("updateShopObjectId"));
    }

    @RequestMapping("/shopObjectUpdate")
    @ResponseBody
    public AjaxRes shopObjectUpdate(ShopObject shopObject){
        return shopObjectService.shopObjectUpdate(shopObject);
    }

    @RequestMapping("/shopObjectDel")
    @ResponseBody
    public AjaxRes shopObjectDel(Integer id){
        return shopObjectService.shopObjectDel(id);
    }

    @RequestMapping("/buyObj")
    @ResponseBody
    public AjaxRes buyObj(Integer id,Integer num,Integer place){
        return shopObjectService.buyObj(id,num,place);
    }

}
