package com.gymmanage.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class User {
    private Integer id;

    private String username;

    private String name;

    private String tel;

    private String idNumber;

    private String pwd;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date roleTime;

    private String role;
}
