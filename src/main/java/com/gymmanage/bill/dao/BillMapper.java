package com.gymmanage.bill.dao;

import com.gymmanage.bill.entity.Bill;
import com.gymmanage.bill.entity.BillItem;
import com.gymmanage.gym.entity.Coach;
import com.gymmanage.shop.entity.ShopObject;
import com.gymmanage.utils.AjaxRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BillMapper {
    List<Bill> selectAll(Integer pay);

    int billAdd(Bill bill);

    int billUpdate(Bill bill);

    int billDel(Integer id);

    Bill getOne(Integer id);

    List<BillItem> getBillItem(Integer placeId);

    List<BillItem> getBillItem2(Integer id);

    Bill getOneByPlaceId(Integer placeId);

    Bill getOneById(Integer id);

    ShopObject getObj(Integer id);

    Coach getCoach(Integer id);
}
