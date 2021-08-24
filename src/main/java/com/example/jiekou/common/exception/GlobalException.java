package com.example.jiekou.common.exception;


import com.example.jiekou.common.enums.ResultCode;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);

        // 然后提取错误提示信息进行返回
        return new Result(ResultCode.VALIDATE_FAILED.code,
                ResultCode.VALIDATE_FAILED.msg,
                objectError.getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result globalExceptionHandler(Exception e){

        e.printStackTrace();
        return new Result(ResultCode.ERROR.code, ResultCode.ERROR.msg);
    }

}
