package com.qf.logistics.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qf.logistics.pojo.ResultData;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 相当于登录 认证
        String username = obtainUsername(request);
        String password = obtainPassword(request);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        setDetails(request, authenticationToken);
        return authenticationManager.authenticate(authenticationToken);
    }

    /**
     * 一旦调用 springSecurity认证登录成功，立即执行该方法
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //登录成功时，返回json格式进行提示
        String username = obtainUsername(request);
        ObjectMapper mapper = new ObjectMapper();
//        Map map = new HashMap();
//        map.put("username", username);
//        map.put("auths", authResult.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray());
//        String jwt = TokenUtil.createToken(mapper.writeValueAsString(map));
        String jwt = TokenUtil.createToken(username);
        response.setContentType("application/json;charset=utf-8");
        final ResultData data = ResultData.createSuccessJsonResult(jwt);
        response.addHeader("token",  jwt);
        response.getWriter().println(mapper.writeValueAsString(data));
        response.getWriter().flush();
        response.getWriter().close();
    }

    /**
     * 一旦调用 springSecurity认证失败 ，立即执行该方法
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException {
        //登录失败时，返回json格式进行提示
        response.setContentType("application/json;charset=utf-8");
//        response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
        PrintWriter out = response.getWriter();
        final ResultData data = ResultData.createFailJsonResult("200001", "账号或密码错误");
//        if (ex instanceof BadCredentialsException) {
//            data.setCode(String.valueOf(HttpServletResponse.SC_BAD_GATEWAY));
//            data.setDesc("账号或密码错误！");
//        }else {
//            // 这里还有其他的 异常 。。 比如账号锁定  过期 等等。。。
//            data.setCode(String.valueOf(HttpServletResponse.SC_BAD_GATEWAY));
//            data.setDesc("登陆失败！");
//        }
        out.write(new ObjectMapper().writeValueAsString(data));
        response.getWriter().flush();
        response.getWriter().close();
    }
}