package com.gymmanage.gym.service.Impl;

import com.gymmanage.gym.dao.CoachMapper;
import com.gymmanage.gym.entity.Coach;
import com.gymmanage.gym.service.CoachService;
import com.gymmanage.shop.dao.ShopObjectMapper;
import com.gymmanage.sys.entity.User;
import com.gymmanage.utils.AjaxRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachServiceImpl implements CoachService {

    @Autowired
    private CoachMapper coachMapper;

    @Autowired
    private ShopObjectMapper shopObjectMapper;

    @Override
    public List<Coach> selectAll() {
        return coachMapper.selectAll();
    }

    @Override
    public AjaxRes coachAdd(Coach coach) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            coachMapper.coachAdd(coach);
            ajaxRes.setMsg("添加成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("添加失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public AjaxRes coachUpdate(Coach coach) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            coachMapper.coachUpdate(coach);
            ajaxRes.setMsg("更新成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("更新失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public AjaxRes coachDel(Integer id) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            coachMapper.coachDel(id);
            ajaxRes.setMsg("删除成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("删除失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public Coach getOne(Integer id) {
        return coachMapper.getOne(id);
    }

    @Override
    public AjaxRes buyCoach(Integer id, Integer place) {
        AjaxRes ajaxRes = new AjaxRes();
        coachMapper.changeState(id,1);
        int billId = shopObjectMapper.getBillIdByPlace(place);
        coachMapper.addToBill(id,billId);
        ajaxRes.setMsg("操作成功");
        ajaxRes.setSuccess(true);
        return ajaxRes;
    }

}
