package com.fastenum.finterface;

import com.fastenum.annotation.FEnumClient;
import com.fastenum.annotation.FEnumMethod;
import com.fastenum.condition.EnumCondition;
import com.fastenum.response.ResponseData;

/**
 * @Description :
 * @Author : zhangMing
 * @Date : Created in 10:22 2020-09-30
 */
@FEnumClient( value = "fenum")
public interface FEnum {

    @FEnumMethod( value = "/enum/enum_by_condititon" , type = "GET")
    ResponseData getCodeEnumsByCodeId(EnumCondition condition);

    @FEnumMethod( value = "/enum/demo" , type = "POST")
    ResponseData post(EnumCondition condition);

}
