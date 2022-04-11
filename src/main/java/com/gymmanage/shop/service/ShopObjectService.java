package com.gymmanage.shop.service;

import com.gymmanage.shop.entity.ShopObject;
import com.gymmanage.utils.AjaxRes;

import java.util.List;

public interface ShopObjectService {
    List<ShopObject> selectAll();

    AjaxRes shopObjectAdd(ShopObject shopObject);

    AjaxRes shopObjectUpdate(ShopObject shopObject);

    AjaxRes shopObjectDel(Integer id);

    ShopObject getOne(Integer id);

    AjaxRes buyObj(Integer id,Integer num,Integer place);

}
