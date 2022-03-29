package com.gymmanage.gym.service;

import com.gymmanage.gym.entity.Book;
import com.gymmanage.gym.entity.Place;
import com.gymmanage.gym.entity.PlaceKind;
import com.gymmanage.utils.AjaxRes;

import java.util.List;

public interface PlaceService {

    List<Place> getAllPlace(Integer kindId);

    String getNextBook(Integer placeId);

    List<PlaceKind> getAllPlaceKind();

    AjaxRes kindAdd(String kind, Integer kindManager);

    List<PlaceKind> kindList();

    AjaxRes placeAdd(Place place);

    AjaxRes placeUpdate(Place place);

    Place getOne(Integer id);

    AjaxRes changeState(Integer id, Integer state,Integer pay);

    List<Place> getPlaceByState(Integer state);

}
