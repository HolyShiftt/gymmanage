package com.gymmanage.gym.dao;

import com.gymmanage.gym.entity.Coach;
import com.gymmanage.sys.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CoachMapper {

    List<Coach> selectAll();

    int coachAdd(Coach coach);

    int coachUpdate(Coach coach);

    int coachDel(Integer id);

    Coach getOne(Integer id);

    void addToBill(Integer id,Integer billId);

    void changeState(Integer id,Integer state);
}
