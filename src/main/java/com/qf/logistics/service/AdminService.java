package com.qf.logistics.service;

import com.qf.logistics.pojo.Admin;
import com.qf.logistics.pojo.ResultData;

/**
 * @Description:
 * @author: Galaxy
 * @Version:
 * @date: 2023/1/10 15:13
 */
public interface AdminService {
    /**
     * 根据用户名查找
     * @param username
     * @return
     */
    Admin findByUsername(String username);

    /**
     * 更新密码
     * @param username
     * @param password
     * @param newPassword
     * @return
     */
    ResultData updatePwd(String username,
                         String password,String newPassword);
}
