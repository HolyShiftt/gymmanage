package com.gymmanage.gym.service;

import com.gymmanage.gym.entity.Book;
import com.gymmanage.sys.entity.User;
import com.gymmanage.utils.AjaxRes;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface BookService {
    List<Book> selectAll();

    AjaxRes apply(Book book);
}
