package com.gymmanage.gym.dao;

import com.gymmanage.gym.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> selectAll();
}
