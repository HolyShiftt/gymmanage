package com.gymmanage.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class indexController {

    @RequestMapping("/")
    public String toPage(HttpSession session) {
        System.out.println(session.getAttribute("username"));
        if (null != session.getAttribute("username")){
            return "/index";
        }else{
            return "/login";
        }

    }

    @RequestMapping("/index")
    public String toIndex() {
        return "/index";
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public String gete(HttpSession session){
        return session.getAttribute("username").toString();
    }
}
