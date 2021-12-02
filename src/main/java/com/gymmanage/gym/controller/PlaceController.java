package com.gymmanage.gym.controller;


import com.gymmanage.gym.entity.Place;
import com.gymmanage.gym.service.PlaceService;
import com.gymmanage.utils.LayuiPage;
import com.gymmanage.utils.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("gym")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @RequestMapping("/placePage")
    public String placePage(){
        return "/gym/place";
    }

    @RequestMapping("/placeAdd")
    public String placeAdd(){
        return "/gym/placeAdd";
    }

    @RequestMapping("/getAllPlace")
    @ResponseBody
    public Table getAllPlace(Integer kindId, LayuiPage layuiPage){
        int length = placeService.getAllPlace(kindId).size();
        return Table.success(Long.valueOf(length),placeService.getAllPlace(kindId));
    }

    @RequestMapping("/getAllPlaceKind")
    @ResponseBody
    public Table getAllPlaceKind(){
        int length = placeService.getAllPlaceKind().size();
        return Table.success(Long.valueOf(length),placeService.getAllPlaceKind());
    }
}
