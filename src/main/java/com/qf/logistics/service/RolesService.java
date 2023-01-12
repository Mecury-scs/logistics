package com.qf.logistics.service;

import com.qf.logistics.pojo.ResultData;
import com.qf.logistics.pojo.Roles;

import java.util.List;

/**
 * @Description:
 * @author: Galaxy
 * @Version:
 * @date: 2023/1/10 18:53
 */
public interface RolesService {
    /**
     * 查询所有
     * @return
     */
    List<Roles> findAll();

    /**
     * 根据权限Id
     * @param roleId
     * @return
     */
    Roles findByRoleId(Integer roleId);

    /**
     * 添加
     * @param roles
     * @return
     */
    ResultData add(Roles roles);

    /**
     * 更新roles
     * @param roles
     * @return
     */
    ResultData update(Roles roles);

    /**
     * 删除
     * @param roleId
     * @return
     */
    ResultData delete(Integer roleId);
}
