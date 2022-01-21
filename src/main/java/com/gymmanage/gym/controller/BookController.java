package com.gymmanage.gym.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gymmanage.gym.entity.Book;
import com.gymmanage.gym.service.BookService;
import com.gymmanage.sys.entity.User;
import com.gymmanage.utils.AjaxRes;
import com.gymmanage.utils.LayuiPage;
import com.gymmanage.utils.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/bookPage")
    public String bookPage(){
        return "/gym/book";
    }

    @RequestMapping("/bookList")
    @ResponseBody
    public Table bookList(LayuiPage layuiPage){
        Page<?> page = PageHelper.startPage(layuiPage.getPage(), layuiPage.getLimit());
        List<Book> book = bookService.selectAll();
        return Table.success(Long.valueOf(page.getTotal()),book);
    }

    @RequestMapping("/apply")
    @ResponseBody
    public AjaxRes apply(Integer placeId, String name, String startTime, String endTime) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd h:mm");
        Book book = new Book();
        book.setId(placeId);
        book.setName(name);
        book.setStart_time(formatter.parse(startTime));
        book.setEnd_time(formatter.parse(endTime));
        return bookService.apply(book);
    }

}
