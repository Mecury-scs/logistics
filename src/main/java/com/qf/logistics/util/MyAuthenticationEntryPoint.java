package com.qf.logistics.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qf.logistics.pojo.ResultData;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 自定义未登录返回
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        final ResultData data = ResultData.createFailJsonResult("200000", "请先登录");
        response.getWriter().println(new ObjectMapper().writeValueAsString(data));
        response.getWriter().flush();
    }
}