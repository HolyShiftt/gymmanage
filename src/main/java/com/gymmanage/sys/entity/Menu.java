package com.gymmanage.sys.entity;

import lombok.Data;

import java.util.List;

@Data
public class Menu {
    private Integer id;

    private String title;

    private String url;

    private Integer parentId;

    private Menu parent;

    private Integer permissionId;

    private List<Menu> children;

    public Menu(){}
    public Menu(Integer id, String title, String url, Integer parentId) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.parentId = parentId;
    }
}
