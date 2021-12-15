package com.gymmanage.sys.service;

import com.gymmanage.sys.entity.Menu;
import java.util.List;

public interface MenuService {
    List<Menu> selectAll(Integer pid);

    List<Menu> getMenuTree();

    void addMenu(String title, String url, Integer parentId);

    void deleteMenu(Integer id);

    void updateMenu(Integer id, String title, String url, Integer parentId);

    List<Integer> getChildrenId(Integer id);
}
