package com.gymmanage.gym.service.Impl;

import com.gymmanage.gym.dao.BookMapper;
import com.gymmanage.gym.dao.PlaceMapper;
import com.gymmanage.gym.entity.Book;
import com.gymmanage.gym.entity.Place;
import com.gymmanage.gym.entity.PlaceKind;
import com.gymmanage.gym.service.BookService;
import com.gymmanage.gym.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> selectAll() {
        return bookMapper.selectAll();
    }
}
