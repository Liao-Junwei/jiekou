package com.example.jiekou.common.exception;


import com.example.jiekou.common.enums.ResultCode;
import lombok.Data;

@Data
public class Result {

    //状态码，比如1000代表响应成功
    private int code;
    //响应信息，用来说明响应情况
    private String msg;
    //响应的具体数据
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public Result(){

    }

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    // 以下枚举出 success、failed、error 的响应体，可直接 Result.success(data)调用
    // 其他状态响应体要 new Result(code, msg, data)
    public static Result success(Object data){
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.code);
        result.setMsg(ResultCode.SUCCESS.msg);
        result.setData(data);
        return result;
//        return new Result(ResultCode.SUCCESS.code, ResultCode.SUCCESS.msg, data);
    }


    public static Result failed(Object data){
        return new Result(ResultCode.FAILED.code, ResultCode.FAILED.msg, data);
    }


    public static Result error(){
        return new Result(ResultCode.ERROR.code, ResultCode.ERROR.msg);
    }
}
