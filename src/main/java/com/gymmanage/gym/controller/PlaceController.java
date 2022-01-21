package com.gymmanage.gym.controller;


import com.gymmanage.gym.entity.Place;
import com.gymmanage.gym.entity.PlaceKind;
import com.gymmanage.gym.service.PlaceService;
import com.gymmanage.sys.entity.User;
import com.gymmanage.utils.AjaxRes;
import com.gymmanage.utils.LayuiPage;
import com.gymmanage.utils.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
@RequestMapping("place")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @RequestMapping("/placePage")
    public String placePage(){
        return "/gym/place";
    }

    @RequestMapping("/placeAddPage")
    public String placeAddPage(){
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

    @RequestMapping("/kindAdd")
    @ResponseBody
    public AjaxRes kindAdd(String kind, Integer kindManager){
        return placeService.kindAdd(kind, kindManager);
    }

    @RequestMapping("/kindList")
    @ResponseBody
    public List<PlaceKind> kindList(){
        return placeService.kindList();
    }

    @RequestMapping("/placeAdd")
    @ResponseBody
    public AjaxRes placeAdd(Place place){
        return placeService.placeAdd(place);
    }
    

}
