package com.gymmanage.shop.service.Impl;

import com.gymmanage.shop.dao.ShopObjectMapper;
import com.gymmanage.shop.entity.ShopObject;
import com.gymmanage.shop.service.ShopObjectService;
import com.gymmanage.utils.AjaxRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopObjectServiceImpl implements ShopObjectService {
    @Autowired
    private ShopObjectMapper shopObjectMapper;

    @Override
    public List<ShopObject> selectAll() {
        return shopObjectMapper.selectAll();
    }

    @Override
    public AjaxRes shopObjectAdd(ShopObject shopObject) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            shopObjectMapper.shopObjectAdd(shopObject);
            ajaxRes.setMsg("添加成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("添加失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public AjaxRes shopObjectUpdate(ShopObject shopObject) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            shopObjectMapper.shopObjectUpdate(shopObject);
            ajaxRes.setMsg("更新成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            System.out.println(e);
            ajaxRes.setMsg("更新失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public AjaxRes shopObjectDel(Integer id) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            shopObjectMapper.shopObjectDel(id);
            ajaxRes.setMsg("删除成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("删除失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public ShopObject getOne(Integer id) {
        return shopObjectMapper.getOne(id);
    }

    @Override
    public AjaxRes buyObj(Integer id, Integer num, Integer place) {
        AjaxRes ajaxRes = new AjaxRes();
        shopObjectMapper.decreaseNum(id, num);
        int billId = shopObjectMapper.getBillIdByPlace(place);
        shopObjectMapper.addToBill(id,num,billId);
        ajaxRes.setMsg("操作成功");
        ajaxRes.setSuccess(true);
        return ajaxRes;
    }
}
