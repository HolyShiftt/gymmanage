package com.gymmanage.gym.service;

import com.gymmanage.gym.entity.Place;
import com.gymmanage.gym.entity.PlaceKind;
import com.gymmanage.utils.AjaxRes;

import java.util.List;

public interface PlaceService {

    List<Place> getAllPlace(Integer kindId);

    List<PlaceKind> getAllPlaceKind();

    AjaxRes kindAdd(String kind, Integer kindManager);

    List<PlaceKind> kindList();

    AjaxRes placeAdd(Place place);
}
