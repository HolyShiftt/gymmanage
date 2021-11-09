package com.gymmanage.gym.service.Impl;

import com.gymmanage.gym.dao.PlaceMapper;
import com.gymmanage.gym.entity.Place;
import com.gymmanage.gym.entity.PlaceKind;
import com.gymmanage.gym.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
