package com.gymmanage.bill.entity;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class BillItem {

    private Integer bill_id;

    private String type;

    private Integer id;

    private Integer num;
}
