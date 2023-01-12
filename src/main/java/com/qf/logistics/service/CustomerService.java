package com.qf.logistics.service;

import com.qf.logistics.pojo.Customer;
import com.qf.logistics.pojo.ResultData;

import java.util.List;

/**
 * @Description:
 * @author: Galaxy
 * @Version:
 * @date: 2023/1/11 20:55
 */
public interface CustomerService {
    List<Customer> findAll();
    ResultData add(Customer customer);
    ResultData delete(Integer userId);
    ResultData update(Customer customer);
}
