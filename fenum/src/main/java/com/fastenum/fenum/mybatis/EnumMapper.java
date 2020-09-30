package com.fastenum.fenum.mybatis;

import com.fastenum.bo.EnumBO;
import com.fastenum.condition.EnumCondition;

import java.util.List;

/**
 * @Description :
 * @Author : zhangMing
 * @Date : Created in 19:01 2020-09-27
 */
public interface EnumMapper {

    /**
     * 条件查询代码枚举值
     * @param condition
     * @return
     */
    List<EnumBO> getEnumBoListByCondition(EnumCondition condition);

}
