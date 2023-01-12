package com.qf.logistics.controller;

import com.qf.logistics.pojo.Company;
import com.qf.logistics.pojo.Customer;
import com.qf.logistics.pojo.ResultData;
import com.qf.logistics.service.CompanyService;
import com.qf.logistics.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @author: Galaxy
 * @Version:
 * @date: 2023/1/11 21:19
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Resource
    private CustomerService customerService;
    @Resource
    private CompanyService companyService;

    @GetMapping("/list")
    public ResultData list(){
        List<Customer> customers = customerService.findAll();
        return ResultData.createSuccessJsonResult(customers);
    }

    @PostMapping("/add")
    public ResultData add(@RequestBody Customer customer){
       return customerService.add(customer);
    }

    @PostMapping("/update")
    public ResultData update(@RequestBody Customer customer){
      return customerService.update(customer);
    }

    @GetMapping("/delete/{userId}")
    public ResultData delete(@PathVariable Integer userId){
       return customerService.delete(userId);
    }

    @GetMapping("comList")
    public ResultData comList(){
        List<Company> companies = companyService.findAll();
        return ResultData.createSuccessJsonResult(companies);
    }
}
