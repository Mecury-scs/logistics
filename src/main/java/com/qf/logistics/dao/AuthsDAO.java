package com.qf.logistics.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.logistics.pojo.Auths;
import com.qf.logistics.pojo.UpdateRoleAuthVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description:
 * @author: Galaxy
 * @Version:
 * @date: 2023/1/11 15:54
 */

public interface AuthsDAO extends BaseMapper<Auths> {
    /**
     * 根据父权限查询
     * @param id
     * @return
     */
    List<Auths> findAllByParentId(Integer id);

    /**
     * 根据权限id查询
     * @param id
     * @return
     */
    List<Auths> findAllByRoleId(Integer id);

    /**
     * 删除权限id
     * @param roleId
     * @return
     */
    int deleteByRoleId(Integer roleId);

    /**
     * 更新权限信息
     * @param vo
     * @return
     */
    int saveAuths(UpdateRoleAuthVO vo);
}
