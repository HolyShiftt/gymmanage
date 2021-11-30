package com.gymmanage.gym.dao;

import com.gymmanage.gym.entity.Place;
import com.gymmanage.gym.entity.PlaceKind;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlaceMapper {

    List<Place> getAllPlace(Integer kindId);

    List<PlaceKind> getAllPlaceKind();
}