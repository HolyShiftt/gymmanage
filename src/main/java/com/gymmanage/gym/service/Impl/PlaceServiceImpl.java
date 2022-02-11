package com.gymmanage.gym.service.Impl;

import com.gymmanage.gym.dao.PlaceMapper;
import com.gymmanage.gym.entity.Book;
import com.gymmanage.gym.entity.Place;
import com.gymmanage.gym.entity.PlaceKind;
import com.gymmanage.gym.service.PlaceService;
import com.gymmanage.utils.AjaxRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceMapper placeMapper;

    @Override
    public List<Place> getAllPlace(Integer kindId) {
        return placeMapper.getAllPlace(kindId);
    }

    @Override
    public List<PlaceKind> getAllPlaceKind() {
        return placeMapper.getAllPlaceKind();
    }

    @Override
    public AjaxRes kindAdd(String kind, Integer kindManager) {
        AjaxRes ajaxRes = new AjaxRes();
        placeMapper.kindAdd(kind);
        int kindId = placeMapper.kindMaxId();
        kind += "管理员";
        placeMapper.kindAddManager(kind,kindId);
        if (kindManager != null){
            int role = placeMapper.roleNewId();
            placeMapper.kindAddUserRole(kindManager,role);
        }
        ajaxRes.setMsg("添加成功");
        ajaxRes.setSuccess(true);
        return ajaxRes;
    }

    @Override
    public List<PlaceKind> kindList() {
        return placeMapper.kindList();
    }

    @Override
    public AjaxRes placeAdd(Place place){
        AjaxRes ajaxRes = new AjaxRes();
        try {
            placeMapper.placeAdd(place);
            ajaxRes.setMsg("保存成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("保存失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public AjaxRes placeUpdate(Place place) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            placeMapper.placeUpdate(place);
            ajaxRes.setMsg("更新成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("更新失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public Place getOne(Integer id) {
        return placeMapper.getOne(id);
    }

    @Override
    public AjaxRes changeState(Integer id, Integer state,Integer pay){
        AjaxRes ajaxRes = new AjaxRes();
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        if (pay == 1){
            placeMapper.payBill(id);
        }else{
            placeMapper.createBill(id,dateFormat.format(date));
        }
        try {
            placeMapper.changeState(id, state);

            ajaxRes.setMsg("操作成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("操作失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }
}
