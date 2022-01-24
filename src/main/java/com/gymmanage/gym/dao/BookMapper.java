package com.gymmanage.gym.dao;

import com.gymmanage.gym.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> selectAll(Integer isCancel, String Order);

    int updPlace(Integer id);

    int apply(Book book);

    int editApply(Book book);

    Book getBookByPlaceId(Integer id);

    int cancelApply1(Integer id);

    int cancelApply2(Integer placeId);
}
