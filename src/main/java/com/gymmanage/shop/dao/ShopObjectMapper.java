package com.gymmanage.shop.dao;

import com.gymmanage.shop.entity.ShopObject;
import com.gymmanage.utils.AjaxRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopObjectMapper {
    List<ShopObject> selectAll();

    int shopObjectAdd(ShopObject shopObject);

    int shopObjectUpdate(ShopObject shopObject);

    int shopObjectDel(Integer id);

    ShopObject getOne(Integer id);

    void decreaseNum(Integer id,Integer num);

    void addToBill(Integer id, Integer num, Integer billId);

    int getBillIdByPlace(Integer place);
}
