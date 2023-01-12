package com.qf.logistics.config;



import com.qf.logistics.service.UserService;
import com.qf.logistics.util.JwtLoginFilter;
import com.qf.logistics.util.JwtVerifyFilter;
import com.qf.logistics.util.MyAccessDeniedHandle;
import com.qf.logistics.util.MyAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @author Galaxy
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 加密器
     */
    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * security中登录的接口
     *
     */
    @Resource
    private UserService userService;

    /**
     * 自定义无权限访问处理方式
     */
    @Resource
    private MyAccessDeniedHandle myAccessDeniedHandle;

    // 自定义未登录返回处理方式
    @Resource
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;


    @Bean
    public PasswordEncoder myPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Remove the ROLE_ prefix
     * 删除jues中的ROLE_前缀
     */
    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // 允许访问
                .antMatchers("/**").permitAll()
//                .antMatchers("/login",
//                        "/swagger-ui.html",
//                        "/webjars/**",
//                        "/swagger-resources/**",
//                        "/v2/**").permitAll()
                .anyRequest().authenticated() // 其他请求拦截
                .and()
                // 允许跨域
                .cors()
                .and()
                .csrf().disable() //关闭csrf
                // jwt登录过滤器
                .addFilter(new JwtLoginFilter(super.authenticationManager()))
                // jwt验证过滤器
                .addFilter(new JwtVerifyFilter(super.authenticationManager()))
                .exceptionHandling()
                .accessDeniedHandler(myAccessDeniedHandle) // 自定义无权限访问
                .authenticationEntryPoint(myAuthenticationEntryPoint) // 自定义未登录返回
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //禁用session
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // UserDetailsService类
        auth.userDetailsService(userService)
                // 加密策略
                .passwordEncoder(passwordEncoder);

    }

    /**
     * 解决 AuthenticationManager 无法注入的问题
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}