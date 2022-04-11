package com.gymmanage.bill.service;
import com.gymmanage.bill.entity.Bill;
import com.gymmanage.utils.AjaxRes;

import java.util.List;

public interface BillService {
    List<Bill> selectAll(Integer pay);

    AjaxRes billAdd(Bill bill);

    AjaxRes billUpdate(Bill bill);

    AjaxRes billDel(Integer id);

    Bill getOne(Integer id);

    Bill getOneByPlaceId(Integer placeId);

    Bill getOneById(Integer id);
}
