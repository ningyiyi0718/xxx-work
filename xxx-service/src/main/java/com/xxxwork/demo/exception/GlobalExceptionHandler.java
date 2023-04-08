package com.xxxwork.demo.exception;

import com.xxxwork.demo.result.ResultCode;
import com.xxxwork.demo.result.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ElevenYang
 * @Description: 全局异常处理
 * @Date 2023/4/8 18:17
 */
@Slf4j
@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResultData handleAccessDeniedException(Throwable e, HttpServletRequest request) {
        String uri = request.getRequestURI();
        log.error("request url: {} exception: {}", uri, e.getMessage(), e);
        return ResultData.error(ResultCode.UNKNOWN_ERROR);
    }

}
