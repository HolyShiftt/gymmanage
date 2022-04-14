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
    public List<Book> selectAll(Integer isCancel) {
        return bookMapper.selectAll(isCancel,"start_time");
    }

    @Override
    public List<Book> clientBookList(Integer userId) {
        return bookMapper.clientBookList(userId,"start_time");
    }

    @Override
    public AjaxRes apply(Book book,Integer id) {
        AjaxRes ajaxRes = new AjaxRes();
        try {
            if (id==null){
                bookMapper.apply(book);
            }else {
                if (bookMapper.checkApply(id).equals("0")){
                    bookMapper.userApply(book.getStartTime(),book.getEndTime(),book.getPlaceId(),book.getName(),id);
                }else{
                    ajaxRes.setMsg("您已经有预约了");
                    ajaxRes.setSuccess(false);
                    return ajaxRes;
                }
            }
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
        int state;
        if (bookMapper.isApply(placeId)!=1 && bookMapper.isApply(placeId)!=0){
            state = 2;
        }else{
            state = 0;
        }
        try {
            bookMapper.cancelApply1(id);
            bookMapper.cancelApply2(placeId,state);
            ajaxRes.setMsg("取消成功");
            ajaxRes.setSuccess(true);
        }catch (Exception e){
            ajaxRes.setMsg("取消失败");
            ajaxRes.setSuccess(false);
        }
        return ajaxRes;
    }
}
