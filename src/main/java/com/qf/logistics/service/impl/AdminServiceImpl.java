package com.qf.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.logistics.dao.AdminDAO;
import com.qf.logistics.pojo.Admin;
import com.qf.logistics.pojo.ResultData;
import com.qf.logistics.service.AdminService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:
 * @author: Galaxy
 * @Version:
 * @date: 2023/1/10 15:13
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDAO adminDAO;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Admin findByUsername(String username) {
        try {
            QueryWrapper<Admin> wrapper = new QueryWrapper<>();
            wrapper.eq("username",username);
            Admin admin = adminDAO.selectOne(wrapper);
            System.out.println(admin);
            return admin;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultData updatePwd(String username, String password, String newPassword) {
        try {
            QueryWrapper<Admin> wrapper = new QueryWrapper<>();
            wrapper.eq("username",username);
            Admin admin = adminDAO.selectOne(wrapper);
            if (admin == null){
                return ResultData.createFailJsonResult("10003","搁着玩呢,用户名被别人改了");
            }else {
                if (passwordEncoder.matches(password,admin.getPassword())){
                    String encodePassword = passwordEncoder.encode(newPassword);
                    admin.setPassword(encodePassword);
                    if (adminDAO.updateById(admin)>0){
                        return ResultData.createSuccessJsonResult("");
                    }else {
                        return ResultData.createFailJsonResult("10004","修改密码失败,请联系你家程序员");
                    }
                }else {
                    return ResultData.createFailJsonResult("10005","原密码记错了,再来");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultData.createFailJsonResult("888888","系统内部错误,稍等再试试");
    }
}
