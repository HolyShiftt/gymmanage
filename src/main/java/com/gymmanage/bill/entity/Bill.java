package com.gymmanage.bill.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;

@ToString
@Data
public class Bill {

    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd h:mm",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd h:mm")
    private String creat_time;

    private Integer client_id;

    private Float total;

    private Integer coach_id;

    private String coach_name;

    private String coach_time;

    private Integer object_id;

    private String object_name;

    private Integer object_num;

    private Integer place_id;

    private String place_name;

    private String place_time;

    private Integer is_pay;

}
