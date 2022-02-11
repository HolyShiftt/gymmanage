package com.gymmanage.gym.dao;

import com.gymmanage.gym.entity.Book;
import com.gymmanage.gym.entity.Place;
import com.gymmanage.gym.entity.PlaceKind;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlaceMapper {

    List<Place> getAllPlace(Integer kindId);

    List<PlaceKind> getAllPlaceKind();

    int kindAdd(String kind);

    int kindMaxId();

    int kindAddManager(String kind, Integer kindId);

    int roleNewId();

    int kindAddUserRole(Integer kindManager, Integer role);

    List<PlaceKind> kindList();

    int placeAdd(Place place);

    int placeUpdate(Place place);

    Place getOne(Integer id);

    int changeState(Integer id, Integer state);

    int payBill(Integer id);

    int createBill(Integer placeId, String time);
}
