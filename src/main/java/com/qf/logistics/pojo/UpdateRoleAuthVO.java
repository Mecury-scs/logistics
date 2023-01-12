package com.qf.logistics.pojo;

import lombok.Data;

import java.util.List;

@Data
public class UpdateRoleAuthVO {
    private Integer roleId;
    private List<Integer> authList;
}
