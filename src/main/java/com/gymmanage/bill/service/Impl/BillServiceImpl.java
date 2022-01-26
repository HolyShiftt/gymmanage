package com.gymmanage.bill.service.Impl;

import com.gymmanage.bill.dao.BillMapper;
import com.gymmanage.bill.entity.Bill;
import com.gymmanage.bill.service.BillService;
import com.gymmanage.utils.AjaxRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillMapper billMapper;

    @Override
    public List<Bill> selectAll(Integer pay) {
        return billMapper.selectAll(pay);
    }

    @Override
    public AjaxRes billAdd(Bill bill) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            billMapper.billAdd(bill);
            ajaxRes.setMsg("添加成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("添加失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public AjaxRes billUpdate(Bill bill) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            billMapper.billUpdate(bill);
            ajaxRes.setMsg("更新成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("更新失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public AjaxRes billDel(Integer id) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            billMapper.billDel(id);
            ajaxRes.setMsg("删除成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("删除失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public Bill getOne(Integer id) {
        return billMapper.getOne(id);
    }
}
