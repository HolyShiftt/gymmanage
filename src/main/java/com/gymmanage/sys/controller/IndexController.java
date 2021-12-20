package com.gymmanage.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String toPage(HttpSession session) {
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


}
