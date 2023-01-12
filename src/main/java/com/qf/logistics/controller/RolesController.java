package com.qf.logistics.controller;

import com.qf.logistics.pojo.ResultData;
import com.qf.logistics.pojo.Roles;
import com.qf.logistics.service.RolesService;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @author: Galaxy
 * @Version:
 * @date: 2023/1/10 19:53
 */
@RestController
@RequestMapping("/role")
public class RolesController {
    @Resource
    private RolesService rolesService;

    @GetMapping("/list")
    public ResultData list(){
        List<Roles> roles = rolesService.findAll();
        if (roles != null){
            return ResultData.createSuccessJsonResult(roles);
        }else {
            return ResultData.createFailJsonResult("10017","压根,没查询到数据啊");
        }
    }

    @PostMapping("/add")
    public ResultData add(@RequestBody Roles roles){
        return rolesService.add(roles);
    }

    @GetMapping("/delete/{roleId}")
    public ResultData delete(@PathVariable Integer roleId){
       return rolesService.delete(roleId);
    }

    @GetMapping("/preUpdate/{roleId}")
    public ResultData preUpdate(@PathVariable Integer roleId){
        Roles byRoleId = rolesService.findByRoleId(roleId);
        return ResultData.createSuccessJsonResult(byRoleId);
    }

    @PostMapping("/update")
    public ResultData update(@RequestBody Roles roles){
        return rolesService.update(roles);
    }
}
