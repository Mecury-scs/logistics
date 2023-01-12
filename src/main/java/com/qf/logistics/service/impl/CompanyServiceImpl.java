package com.qf.logistics.service.impl;

import com.qf.logistics.dao.CompanyDAO;
import com.qf.logistics.pojo.Company;
import com.qf.logistics.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @author: Galaxy
 * @Version:
 * @date: 2023/1/11 21:50
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    @Resource
    private CompanyDAO companyDAO;

    @Override
    public List<Company> findAll() {
        return companyDAO.selectList(null);
    }
}
