package com.gymmanage.gym.service.Impl;

import com.gymmanage.gym.dao.BookMapper;
import com.gymmanage.gym.dao.PlaceMapper;
import com.gymmanage.gym.entity.Book;
import com.gymmanage.gym.entity.Place;
import com.gymmanage.gym.entity.PlaceKind;
import com.gymmanage.gym.service.BookService;
import com.gymmanage.gym.service.PlaceService;
import com.gymmanage.utils.AjaxRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> selectAll() {
        return bookMapper.selectAll("start_time");
    }

    @Override
    public AjaxRes apply(Book book) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            bookMapper.apply(book);
            bookMapper.updPlace(book.getPlaceId());
            ajaxRes.setMsg("预约成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("预约失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public AjaxRes editApply(Book book) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            bookMapper.editApply(book);
            ajaxRes.setMsg("修改成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("修改失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }

    @Override
    public Book getBookByPlaceId(Integer id) {
        return bookMapper.getBookByPlaceId(id);
    }

    @Override
    public AjaxRes cancelApply(Integer id, Integer placeId) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            bookMapper.cancelApply1(id);
            bookMapper.cancelApply2(placeId);
            ajaxRes.setMsg("取消成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("取消失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }
}
