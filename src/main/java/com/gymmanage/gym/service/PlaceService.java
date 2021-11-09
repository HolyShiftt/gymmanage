package com.gymmanage.gym.service;

import com.gymmanage.gym.entity.Place;
import com.gymmanage.gym.entity.PlaceKind;

import java.util.List;

public interface PlaceService {

    List<Place> getAllPlace(Integer kindId);

    List<PlaceKind> getAllPlaceKind();
}
