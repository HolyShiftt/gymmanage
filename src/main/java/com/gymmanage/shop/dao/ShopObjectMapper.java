package com.gymmanage.shop.dao;

import com.gymmanage.shop.entity.ShopObject;
import com.gymmanage.utils.AjaxRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopObjectMapper {
    List<ShopObject> selectAll();

    AjaxRes shopObjectAdd(ShopObject shopObject);

    AjaxRes shopObjectUpdate(ShopObject shopObject);

    AjaxRes shopObjectDel(Integer id);

    ShopObject getOne(Integer id);
}
