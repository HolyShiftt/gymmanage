package com.gymmanage.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class indexController {

    @RequestMapping("/login")
    public String toLogin() {
        return "/login";
    }

    @RequestMapping("/index")
    public String toIndex() {
        return "/index";
    }
}
