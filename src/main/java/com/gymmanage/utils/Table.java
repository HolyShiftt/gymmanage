package com.gymmanage.utils;

import lombok.Data;

import java.io.Serializable;

@Data
/*
 * 用于返回layui表格所需要的json数据格式
 */
public class Table implements Serializable {
    private static final Integer SUCCESS_CODE = Integer.valueOf(0);
    private static final String SUCCESS_MSG = "success";
    private static final Integer FAIL_CODE = Integer.valueOf(-1);
    private static final String FAIL_MSG = "fail";

    public Table(Integer code, String msg, Long count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public Table(){}

    public static Table newInstance(Integer code, String msg, Long count, Object data){
        return new Table(code,msg,count,data);
    }

    public static Table success(Long count,Object data){
        return newInstance(SUCCESS_CODE,SUCCESS_MSG,count,data);
    }
    public static Table success(Object data){
        return newInstance(SUCCESS_CODE,SUCCESS_MSG, (long) 0,data);
    }

    private Integer code;
    private String msg;
    private Long count;
    private Object data;

}
