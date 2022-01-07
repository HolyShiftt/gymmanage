package com.gymmanage.gym.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ToString
@Data
public class Book {

    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd h:mm",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd h:mm")
    private Date start_time;

    @JsonFormat(pattern = "yyyy-MM-dd h:mm",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd h:mm")
    private Date end_time;

    private Integer placeId;

    private String placeName;

    private Integer userId;

    private String userName;

    private Integer clientId;
}
