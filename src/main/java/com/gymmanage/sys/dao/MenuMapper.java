package com.gymmanage.sys.dao;

import com.gymmanage.sys.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    Menu selectByPrimaryKey(Integer id);

    List<Menu> selectAll(Integer pid);

    int updateByPrimaryKey(Menu record);

    List<Menu> menuTree();

    List<Integer> getChildrenId(Integer id);
}
