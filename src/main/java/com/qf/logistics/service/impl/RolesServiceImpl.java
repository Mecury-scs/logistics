package com.qf.logistics.service.impl;

import com.qf.logistics.dao.RolesDAO;
import com.qf.logistics.pojo.ResultData;
import com.qf.logistics.pojo.Roles;
import com.qf.logistics.service.RolesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @author: Galaxy
 * @Version:
 * @date: 2023/1/10 18:54
 */
@Service
public class RolesServiceImpl implements RolesService {
    @Resource
    private RolesDAO rolesDAO;

    @Override
    public List<Roles> findAll() {
        try {
            return rolesDAO.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Roles findByRoleId(Integer roleId) {
        try {
            return rolesDAO.selectById(roleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultData add(Roles roles) {
        roles.setState(1);
      if ( rolesDAO.insert(roles)>0){
          return ResultData.createSuccessJsonResult("添加成功");
      }else {
          return ResultData.createFailJsonResult("10007","添加失败,请联系攻城狮");
      }

    }

    @Override
    public ResultData update(Roles roles) {
       if (rolesDAO.updateById(roles)>0){
           return ResultData.createSuccessJsonResult("");
       }else {
           return ResultData.createFailJsonResult("10004","修改失败,联系IT技术");
       }

    }

    @Override
    public ResultData delete(Integer roleId) {
        Roles roles = rolesDAO.selectById(roleId);
        if (roles != null){
            roles.setState(3);
            if (rolesDAO.updateById(roles)>0){
                return ResultData.createSuccessJsonResult("");
            }else {
                return ResultData.createFailJsonResult("10008","删除失败,请联系程序猿");
            }

        }else {
            return ResultData.createFailJsonResult("10009","这名用户飞到外太空了,删除失败");
        }
    }
}
