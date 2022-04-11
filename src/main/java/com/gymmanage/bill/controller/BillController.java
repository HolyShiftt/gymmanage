package com.gymmanage.bill.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gymmanage.bill.entity.Bill;
import com.gymmanage.bill.service.BillService;
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
@RequestMapping("bill")
public class BillController {

    @Autowired
    private BillService billService;

    @RequestMapping("billPage")
    public String billPage(){
        return "/bill/bill";
    }

    @RequestMapping("billAddPage")
    public String billAddPage(){
        return "/bill/billAdd";
    }

    @RequestMapping("billUpdatePage")
    public String billUpdatePage(Integer id, HttpSession session){
        session.setAttribute("updateBillId",id);
        return "/bill/billUpdate";
    }

    @RequestMapping("/billList")
    @ResponseBody
    public Table billList(Integer pay,LayuiPage layuiPage){
        Page<?> page = PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());
        List<Bill> bill = billService.selectAll(pay);
        return Table.success(Long.valueOf(page.getTotal()),bill);
    }

    @RequestMapping("/billAdd")
    @ResponseBody
    public AjaxRes billAdd(Bill bill){
        return billService.billAdd(bill);
    }

    @RequestMapping("/getOne")
    @ResponseBody
    public Bill getOne(HttpSession session){
        return billService.getOne((Integer)session.getAttribute("updateBillId"));
    }

    @RequestMapping("/getOneByPlaceId")
    @ResponseBody
    public Bill getOneByPlaceId(Integer placeId){
        return billService.getOneByPlaceId(placeId);
    }
    @RequestMapping("/getOneById")
    @ResponseBody
    public Bill getOneById(Integer id){
        return billService.getOneById(id);
    }

    @RequestMapping("/billUpdate")
    @ResponseBody
    public AjaxRes billUpdate(Bill bill){
        return billService.billUpdate(bill);
    }

    @RequestMapping("/billDel")
    @ResponseBody
    public AjaxRes billDel(Integer id){
        return billService.billDel(id);
    }
}
