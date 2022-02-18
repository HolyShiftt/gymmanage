package com.gymmanage.gym.service;

import com.gymmanage.gym.entity.Book;
import com.gymmanage.sys.entity.User;
import com.gymmanage.utils.AjaxRes;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface BookService {
    List<Book> selectAll(Integer isCancel);

    List<Book> clientBookList(Integer userId);

    AjaxRes apply(Book book,Integer id);

    AjaxRes editApply(Book book);

    Book getBookByPlaceId(Integer id);

    AjaxRes cancelApply(Integer id,Integer placeId);
}
