package com.qf.logistics.service.impl;

import com.qf.logistics.dao.CustomerDAO;
import com.qf.logistics.pojo.Customer;
import com.qf.logistics.pojo.ResultData;
import com.qf.logistics.service.CustomerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @author: Galaxy
 * @Version:
 * @date: 2023/1/11 21:18
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerDAO customerDAO;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = customerDAO.selectList(null);
        for (Customer customer : customers) {
            System.out.println(customer);
        }
        return customers;
    }

    @Override
    public ResultData add(Customer customer) {
        try {
            customer.setState(1);
            String encode = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(encode);
            if (customerDAO.insert(customer)>0){
               return ResultData.createSuccessJsonResult("");
            }else {
                return  ResultData.createFailJsonResult("30001","添加失败,毁灭吧");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultData delete(Integer userId) {
        Customer customer = customerDAO.selectById(userId);
        customer.setState(3);
        if ( customerDAO.updateById(customer)>0){
            return ResultData.createSuccessJsonResult("删除成功");
        }else {
            return ResultData.createFailJsonResult("30001","删除失败,删除失败,还玩不玩");
        }
    }

    @Override
    public ResultData update(Customer customer) {
        if (customerDAO.updateById(customer)>0){
            return ResultData.createSuccessJsonResult("更新成功");
        }else {
            return ResultData.createFailJsonResult("30003","更新失败,技术员");
        }
    }
}
