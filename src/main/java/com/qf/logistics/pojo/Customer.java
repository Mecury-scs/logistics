package com.qf.logistics.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * @author Galaxy
 */
@Data
@TableName(value = "users",resultMap = "customerMap")
public class Customer {
  @TableId(value = "user_id",type = IdType.AUTO)
  private Integer userId;
  private String loginname;
  private String pwd;
  private String username;
  private String tel;
  private String email;
  private Integer roleId;
  private Integer comId;
  private Integer state;
  @TableField(exist = false)
  private Roles roles;
  @TableField(exist = false)
  private Company company;
}
