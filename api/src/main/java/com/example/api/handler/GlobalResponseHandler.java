package com.example.api.handler;

import com.example.api.annotation.DisableBaseResponse;
import com.example.api.model.support.ResponseResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一拦截Controller中所有方法的返回值
 * 封装后返回ResponseResult<T>
 */
@ControllerAdvice(value = "com.example.api.controller")
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter methodParameter, Class c) {
        return !methodParameter.hasParameterAnnotation(DisableBaseResponse.class);
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return o == null ? new ResponseResult<>() : new ResponseResult<>(o);
    }

}
