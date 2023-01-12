package com.qf.logistics.service.impl;

import com.qf.logistics.pojo.Admin;
import com.qf.logistics.service.AdminService;
import com.qf.logistics.service.UserService;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:
 * @author: Galaxy
 * @Version:
 * @date: 2023/1/10 15:06
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查找用户信息
        final Admin admin = adminService.findByUsername(username);

//        System.out.println(passwordEncoder.encode("123456"));

        if (admin == null){
            return null;
        }else {
            String authorityString = "";
//            // 查询所有的角色
//            final List<String> roles = usersService.findRolesByUserId(users.getId());
//            final String s2 = String.join(",", roles);
//
//            // 查询所有的权限
//            final List<String> auths = usersService.findAuthsByUserId(users.getId());
//            final String s1 = String.join(",", auths);
//
//            authorityString = s2 + "," + s1;
//            System.out.println(authorityString);

            return new User(admin.getUsername(), admin.getPassword(),
                    AuthorityUtils.commaSeparatedStringToAuthorityList(authorityString)); //账号 密码 权限
        }
    }
}