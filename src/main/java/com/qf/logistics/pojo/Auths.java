package com.qf.logistics.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Galaxy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(value = "auths", resultMap = "authsMap")
public class Auths {

  @TableId(value = "auth_id",type = IdType.AUTO)
  private Integer authId;
  private String authName;
  private String menuUrl;
  private Integer parentId;

  @TableField(exist = false)
  private List<Auths> authsList;

}
