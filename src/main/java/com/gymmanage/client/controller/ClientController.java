package com.gymmanage.client.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gymmanage.client.entity.Client;
import com.gymmanage.client.service.ClientService;
import com.gymmanage.utils.AjaxRes;
import com.gymmanage.utils.LayuiPage;
import com.gymmanage.utils.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping("clientPage")
    public String clientPage(){
        return "/client/client";
    }

    @RequestMapping("clientAddPage")
    public String clientAddPage(){
        return "/client/clientAdd";
    }

    @RequestMapping("clientUpdatePage")
    public String clientUpdatePage(Integer id, HttpSession session){
        session.setAttribute("updateClientId",id);
        return "/client/clientUpdate";
    }

    @RequestMapping("/clientList")
    @ResponseBody
    public Table clientList(LayuiPage layuiPage){
        Page<?> page = PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());
        List<Client> client = clientService.selectAll();
        return Table.success(Long.valueOf(page.getTotal()),client);
    }

    @RequestMapping("/clientAdd")
    @ResponseBody
    public AjaxRes clientAdd(Client client){
        return clientService.clientAdd(client);
    }

    @RequestMapping("/getOne")
    @ResponseBody
    public Client getOne(HttpSession session){
        return clientService.getOne((Integer)session.getAttribute("updateClientId"));
    }

    @RequestMapping("/clientUpdate")
    @ResponseBody
    public AjaxRes clientUpdate(Client client){
        return clientService.clientUpdate(client);
    }

    @RequestMapping("/clientDel")
    @ResponseBody
    public AjaxRes clientDel(Integer id){
        return clientService.clientDel(id);
    }
}
