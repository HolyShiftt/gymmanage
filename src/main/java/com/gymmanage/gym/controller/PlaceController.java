package com.gymmanage.gym.controller;


import com.gymmanage.gym.entity.Book;
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

import javax.servlet.http.HttpSession;
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

    @RequestMapping("/placeUpdatePage")
    public String placeUpdatePage(Integer id, HttpSession session){
        session.setAttribute("updatePlaceId",id);
        return "/gym/placeUpdate";
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

    @RequestMapping("/getOne")
    @ResponseBody
    public Place getOne(HttpSession session){
        return placeService.getOne((Integer)session.getAttribute("updatePlaceId"));
    }

    @RequestMapping("/placeAdd")
    @ResponseBody
    public AjaxRes placeAdd(Place place){
        return placeService.placeAdd(place);
    }

    @RequestMapping("/placeUpdate")
    @ResponseBody
    public AjaxRes placeUpdate(Place place,HttpSession session){
        place.setId((Integer)session.getAttribute("updatePlaceId"));
        return placeService.placeUpdate(place);
    }

    @RequestMapping("/changeState")
    @ResponseBody
    public AjaxRes changeState(Integer id,Integer state,Integer pay){
        return placeService.changeState(id, state,pay);
    }

}
