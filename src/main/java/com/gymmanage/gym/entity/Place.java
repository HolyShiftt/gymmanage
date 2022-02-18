package com.gymmanage.gym.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ToString
@Data
public class Place {
    private Integer id;

    private String name;

    private Integer kindId;

    private String kindName;

    private String size;

    private String managerName;

    private String price;

    private Integer state;

    private String nextBook;
}
