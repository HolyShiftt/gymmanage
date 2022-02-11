package com.gymmanage.bill.dao;

import com.gymmanage.bill.entity.Bill;
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
}
