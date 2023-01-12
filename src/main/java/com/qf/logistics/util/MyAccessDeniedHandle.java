package com.qf.logistics.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qf.logistics.pojo.ResultData;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 自定义无权限访问
@Component
public class MyAccessDeniedHandle implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        // 这里写死只做测试  请以实际为主
        final ResultData data = ResultData.createFailJsonResult("50000001", "您没有权限");
        response.getWriter().println(new ObjectMapper().writeValueAsString(data));
        response.getWriter().flush();
    }
}