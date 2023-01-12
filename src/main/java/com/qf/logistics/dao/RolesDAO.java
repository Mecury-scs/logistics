package com.qf.logistics.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.logistics.pojo.Roles;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description:
 * @author: Galaxy
 * @Version:
 * @date: 2023/1/10 17:45
 */

public interface RolesDAO extends BaseMapper<Roles> {
    /**
     *
     * @return
     */
    List<Roles> findAll();
}
