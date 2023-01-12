package com.qf.logistics.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Galaxy
 */
@Data
@TableName("admin")
public class Admin {
  @TableId(type = IdType.AUTO)
  private Integer id;
  private String username;
  private String password;
}
