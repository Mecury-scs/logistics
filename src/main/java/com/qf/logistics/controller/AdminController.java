package com.qf.logistics.controller;

import com.qf.logistics.pojo.ResultData;
import com.qf.logistics.service.AdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author Galaxy
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    @PostMapping("/updatePwd")
    public ResultData updatePassword(String newPassword, String password, String username){
        return adminService.updatePwd(username, password, newPassword);
    }
}
