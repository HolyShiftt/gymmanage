package com.gymmanage.client.entity;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Client {

    private Integer id;

    private String user_name;

    private String tel;

    private String sex;

    private Integer age;

    private String birth;

    private String address;

    private Integer is_vip;

    private String vip_time;

    private String pwd;

}
