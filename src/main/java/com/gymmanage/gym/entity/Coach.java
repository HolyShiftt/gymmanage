package com.gymmanage.gym.entity;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Coach {

    private Integer id;

    private String name;

    private String sex;

    private Integer age;

    private String course;

    private String price;

    private Integer state;

    private Integer time;

}
