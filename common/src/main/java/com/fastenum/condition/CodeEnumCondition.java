package com.fastenum.condition;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description : 查询条件实体
 * @Author : zhangMing
 * @Date : Created in 18:30 2020-09-27
 */
@Data
public class CodeEnumCondition {

    /**
     * 代码编号
     */
    @NotBlank(message="代码编号不能为空")
    private String codeId;

    /**
     * 枚举值编号
     */
    private String enumId;

    public CodeEnumCondition(@NotBlank(message="代码编号不能为空") String codeId) {
        this.codeId=codeId;
    }

    public CodeEnumCondition(@NotBlank(message="代码编号不能为空") String codeId, String enumId) {
        this.codeId=codeId;
        this.enumId=enumId;
    }
}
