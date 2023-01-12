package com.qf.logistics.service.impl;

import com.qf.logistics.dao.AuthsDAO;
import com.qf.logistics.pojo.Auths;
import com.qf.logistics.pojo.UpdateRoleAuthVO;
import com.qf.logistics.service.AuthsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @author: Galaxy
 * @Version:
 * @date: 2023/1/11 16:19
 */
@Service
public class AuthsServiceImpl implements AuthsService {
    @Resource
    private AuthsDAO authsDAO;
    @Override
    public List<Auths> findAllByParentId() {
        try {
            return authsDAO.findAllByParentId(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Auths> findAllByRoleId(Integer id) {
        try {
            return authsDAO.findAllByRoleId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateAuth(UpdateRoleAuthVO vo) {
        try {
            authsDAO.deleteByRoleId(vo.getRoleId());
            authsDAO.saveAuths(vo);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
