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
    private String startTime;

    @JsonFormat(pattern = "yyyy-MM-dd h:mm",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd h:mm")
    private String endTime;

    private Integer placeId;

    private String placeName;

    private String name;

    private Integer is_cancel;

    private Integer is_arrive;
}
