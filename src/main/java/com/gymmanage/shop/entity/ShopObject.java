package com.gymmanage.shop.entity;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ShopObject {

    private Integer id;

    private String name;

    private Float price;

    private String desc;

    private Integer num;

    private Integer billNum;
}
