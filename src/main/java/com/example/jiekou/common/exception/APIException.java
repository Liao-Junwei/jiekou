package com.example.jiekou.common.exception;


import lombok.Data;

@Data
public class APIException extends RuntimeException{

    public int code;
    public String msg;


    public APIException(String msg) {
        this.msg = msg;
    }

    public APIException(String msg, int code) {
        this.code = code;
        this.msg = msg;
    }

    public APIException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }


}
