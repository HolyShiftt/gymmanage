package com.gymmanage.bill.service.Impl;

import com.gymmanage.bill.dao.BillMapper;
import com.gymmanage.bill.entity.Bill;
import com.gymmanage.bill.entity.BillItem;
import com.gymmanage.bill.service.BillService;
import com.gymmanage.gym.entity.Coach;
import com.gymmanage.shop.entity.ShopObject;
import com.gymmanage.utils.AjaxRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public Bill getOneByPlaceId(Integer placeId) {
        List<ShopObject> objectList = new ArrayList<ShopObject>();
        List<Coach> coachList = new ArrayList<Coach>();
        Bill oneByPlaceId = billMapper.getOneByPlaceId(placeId);
        try{
            List<BillItem> billItem = billMapper.getBillItem(placeId);
            for (BillItem o : billItem) {
                if (o.getType().equals("obj")){
                    ShopObject obj = billMapper.getObj(o.getId());
                    obj.setBillNum(o.getNum());
                    objectList.add(obj);
                }else if (o.getType().equals("coach")){
                    Coach coach = billMapper.getCoach(o.getId());
                    coach.setTime(o.getNum());
                    coachList.add(coach);
                }
            }
            oneByPlaceId.setObjectList(objectList);
            oneByPlaceId.setCoachList(coachList);
        }catch (Exception e){

        }
        return oneByPlaceId;
    }

    @Override
    public Bill getOneById(Integer id) {
        List<ShopObject> objectList = new ArrayList<ShopObject>();
        List<Coach> coachList = new ArrayList<Coach>();
        Bill oneByPlaceId = billMapper.getOneById(id);
        try {

            List<BillItem> billItem = billMapper.getBillItem2(id);
            if(billItem.size()!=0){
                for (BillItem o : billItem) {
                    if (o.getType().equals("obj")){
                        ShopObject obj = billMapper.getObj(o.getId());
                        obj.setBillNum(o.getNum());
                        objectList.add(obj);
                    }else if (o.getType().equals("coach")){
                        Coach coach = billMapper.getCoach(o.getId());
                        coach.setTime(o.getNum());
                        coachList.add(coach);
                    }
                }
                oneByPlaceId.setObjectList(objectList);
                oneByPlaceId.setCoachList(coachList);
            }

        }catch (Exception e){

        }
        return oneByPlaceId;

    }
}
