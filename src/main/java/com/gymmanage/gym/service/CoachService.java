package com.gymmanage.gym.service;

import com.gymmanage.gym.entity.Coach;
import com.gymmanage.sys.entity.User;
import com.gymmanage.utils.AjaxRes;

import java.util.List;

public interface CoachService {
    List<Coach> selectAll();

    AjaxRes coachAdd(Coach coach);

    AjaxRes coachUpdate(Coach coach);

    AjaxRes coachDel(Integer id);

    Coach getOne(Integer id);

    AjaxRes buyCoach(Integer id,Integer place);

}
