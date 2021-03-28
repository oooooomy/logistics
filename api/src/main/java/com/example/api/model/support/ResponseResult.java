package com.example.api.model.support;

import lombok.Data;

@Data
public class ResponseResult<T> {

    private Integer code;

    private boolean status;

    private String msg;

    private T data;

    public ResponseResult() {
        this.code = 200;
        this.status = true;
        this.msg = null;
        this.data = null;
    }

    //不附带msg返回数据
    public ResponseResult(T data) {
        this.code = 200;
        this.status = true;
        this.msg = null;
        this.data = data;
    }

    //默认附带msg和data
    public ResponseResult(String msg, T data) {
        this.code = 200;
        this.status = true;
        this.msg = msg;
        this.data = data;
    }

    //默认的错误返回
    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.status = false;
        this.msg = msg;
        this.data = null;
    }

}
