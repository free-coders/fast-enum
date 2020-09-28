package com.fastenum.bo;

import lombok.Data;

import java.util.Date;

/**
 * @Description : 枚举实体
 * @Author : zhangMing
 * @Date : Created in 18:37 2020-09-27
 */
@Data
public class EnumBO {

    /**
     * 枚举值编号
     */
    private String enumId;

    /**
     * 枚举值描述
     */
    private String enumDesc;

    /**
     * 语种
     */
    private String language;

    /**
     * 枚举值状态. 0为禁用，1为启用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createDatetime;

    /**
     * 更新时间
     */
    private Date modDatetime;

}
