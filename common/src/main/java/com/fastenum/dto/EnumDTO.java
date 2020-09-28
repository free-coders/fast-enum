package com.fastenum.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description :
 * @Author : zhangMing
 * @Date : Created in 18:38 2020-09-27
 */
@Data
public class EnumDTO {

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
    private String status;

    /**
     * 创建时间
     */
    private Date createDateTime;

    /**
     * 更新时间
     */
    private Date updateDateTime;

}
