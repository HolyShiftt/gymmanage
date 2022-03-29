package com.gymmanage.bill.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gymmanage.gym.entity.Coach;
import com.gymmanage.shop.entity.ShopObject;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.List;

@ToString
@Data
public class Bill {

    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd h:mm",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd h:mm")
    private String creat_time;

    private Float total;

    private List<Coach> coachList;

    private List<ShopObject> objectList;

    private Integer place_id;

    private String place_name;

    private String place_time;

    private String username;

    private Integer place_price;

    private Integer is_pay;

}
