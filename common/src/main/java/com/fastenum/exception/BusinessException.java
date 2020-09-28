package com.fastenum.exception;

import com.fastenum.bo.EnumBO;
import lombok.Data;

/**
 * @Description : 异常封装
 * @Author : zhangMing
 * @Date : Created in 18:35 2020-09-27
 */
@Data
public class BusinessException extends RuntimeException {

    private CommonError commonError;

    public BusinessException(EnumBO errorEnumBo){
        super();
        this.commonError = new CommonError( errorEnumBo );
    }

    public BusinessException(EnumBO errorEnumBo ,String errMsg){
        super();
        this.commonError = new CommonError( errorEnumBo );
        this.commonError.setErrMsg( errMsg );
    }
}
