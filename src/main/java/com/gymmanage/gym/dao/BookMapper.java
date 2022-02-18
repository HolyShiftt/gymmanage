package com.gymmanage.gym.dao;

import com.gymmanage.gym.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> selectAll(Integer isCancel, String Order);

    List<Book> clientBookList(Integer userId, String Order);

    int updPlace(Integer id);

    int apply(Book book);

    String checkApply(Integer id);

    int userApply(String startTime,String endTime,Integer placeId, String name,Integer userId);

    int editApply(Book book);

    Book getBookByPlaceId(Integer id);

    int cancelApply1(Integer id);

    int isApply(Integer placeId);

    int cancelApply2(Integer placeId ,Integer state);

    int applyArrive(String id);
}
