package com.gymmanage.gym.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gymmanage.gym.entity.Coach;
import com.gymmanage.gym.entity.Place;
import com.gymmanage.gym.service.CoachService;
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
@RequestMapping("coach")
public class CoachController {

    @Autowired
    private CoachService coachService;

    @RequestMapping("coachPage")
    public String coachPage(){
        return "/gym/coach";
    }

    @RequestMapping("coachAddPage")
    public String coachAddPage(){
        return "/gym/coachAdd";
    }

    @RequestMapping("coachUpdatePage")
    public String coachUpdatePage(Integer id, HttpSession session){
        session.setAttribute("updateCoachId",id);
        return "/gym/coachUpdate";
    }

    @RequestMapping("/coachList")
    @ResponseBody
    public Table coachList(LayuiPage layuiPage){
        Page<?> page = PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());
        List<Coach> coach = coachService.selectAll();
        return Table.success(Long.valueOf(page.getTotal()),coach);
    }

    @RequestMapping("/coachAdd")
    @ResponseBody
    public AjaxRes coachAdd(Coach coach){
        return coachService.coachAdd(coach);
    }

    @RequestMapping("/getOne")
    @ResponseBody
    public Coach getOne(HttpSession session){
        return coachService.getOne((Integer)session.getAttribute("updateCoachId"));
    }

    @RequestMapping("/coachUpdate")
    @ResponseBody
    public AjaxRes coachUpdate(Coach coach){
        return coachService.coachUpdate(coach);
    }

    @RequestMapping("/coachDel")
    @ResponseBody
    public AjaxRes coachDel(Integer id){
        return coachService.coachDel(id);
    }


    @RequestMapping("/buyCoach")
    @ResponseBody
    public AjaxRes buyCoach(Integer id,Integer place){
        return coachService.buyCoach(id,place);
    }
}
