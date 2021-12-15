package com.gymmanage.sys.service.Impl;

import com.gymmanage.sys.dao.MenuMapper;
import com.gymmanage.sys.entity.Menu;
import com.gymmanage.sys.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> selectAll(Integer pid) {
        return menuMapper.selectAll(pid);
    }

    @Override
    public List<Menu> getMenuTree() {
        return menuMapper.menuTree();
    }

    @Override
    public void addMenu(String title, String url, Integer parentId) {
        Menu menu = new Menu();
        menu.setTitle(title);
        menu.setUrl(url);
        menu.setParentId(parentId);
        menuMapper.insert(menu);
    }

    @Override
    public void deleteMenu(Integer id){
        List<Integer> childrenId = menuMapper.getChildrenId(id);
        for (Integer cId : childrenId) {
            menuMapper.deleteByPrimaryKey(cId);
        }
        menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateMenu(Integer id, String title, String url, Integer parentId) {
        Menu menu = new Menu(id, title, url, parentId);
        menuMapper.updateByPrimaryKey(menu);
    }

    @Override
    public List<Integer> getChildrenId(Integer id) {
       return menuMapper.getChildrenId(id);
    }
}
