package com.qf.logistics.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author Galaxy
 */
@Data
public class Roles {

  @TableId(value = "role_id",type = IdType.AUTO)
  private Integer roleId;
  private String roleName;
  private String roleNumber;
  private String roleDesc;
  /**
   * 1表示账户正常
   * 2表示账户冻结
   * 3表示账户删除
   */
  private Integer state;

}
