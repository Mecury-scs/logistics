package com.qf.logistics.service;

import com.qf.logistics.pojo.Auths;
import com.qf.logistics.pojo.UpdateRoleAuthVO;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @Description:
 * @author: Galaxy
 * @Version:
 * @date: 2023/1/11 16:18
 */
public interface AuthsService {
    /**
     * 根据父权限查询权限内容
     * @return
     */
    List<Auths> findAllByParentId();


    List<Auths> findAllByRoleId(Integer id);


    void updateAuth(UpdateRoleAuthVO vo);

}
