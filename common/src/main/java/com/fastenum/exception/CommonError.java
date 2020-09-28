package com.fastenum.exception;

import com.fastenum.bo.EnumBO;
import lombok.Data;

/**
 * @Description : 错误码封装层
 * @Author : zhangMing
 * @Date : Created in 18:35 2020-09-27
 */
@Data
public class CommonError {

    /**
     * 错误码
     */
    private String errCode;

    /**
     * 错误描述
     */
    private String errMsg;

    public CommonError(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public CommonError(EnumBO errorEnumBO){
        this.errCode = errorEnumBO.getEnumId();
        this.errMsg = errorEnumBO.getEnumDesc();
    }

}
