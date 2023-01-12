package com.qf.logistics.controller;

import com.qf.logistics.pojo.Auths;
import com.qf.logistics.pojo.ResultData;
import com.qf.logistics.pojo.UpdateRoleAuthVO;
import com.qf.logistics.service.AuthsService;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @author: Galaxy
 * @Version:
 * @date: 2023/1/11 16:29
 */
@RestController
@RequestMapping("/auths")
public class AuthsController {
    @Resource
    private AuthsService authsService;

    @GetMapping("/list")
    public ResultData list(){
        List<Auths> allByParentId = authsService.findAllByParentId();
        if (allByParentId == null){
            return ResultData.createFailJsonResult("20002","没找到数据");
        }else {
            return ResultData.createSuccessJsonResult(allByParentId);
        }
    }

    @GetMapping("/findAllByRoleId")
    public ResultData findAllByRoleId(Integer roleId){
        final List<Auths> list = authsService.findAllByRoleId(roleId);
        if (list != null){
            return ResultData.createSuccessJsonResult(list);
        }else {
            return ResultData.createFailJsonResult("300003", "查询权限数据失败");
        }
    }

    @PostMapping("/updateAuths")
    public ResultData updateAuths(@RequestBody UpdateRoleAuthVO vo){
        authsService.updateAuth(vo);
        return ResultData.createSuccessJsonResult("");
    }
}
