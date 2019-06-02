package com.mxw.doraemon.common;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 基础父类
 * @program: doraemon
 * @description:
 * @author: AlanMa
 * @create: 2019-05-29 18:14
 */
@Data
@Accessors(chain = true)
public class BaseEntity {

  @TableId(type = IdType.AUTO)
  private Long id;

}
