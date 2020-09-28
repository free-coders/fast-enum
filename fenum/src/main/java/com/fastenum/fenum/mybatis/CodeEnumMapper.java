package com.fastenum.fenum.mybatis;

import com.fastenum.bo.EnumBO;
import com.fastenum.condition.CodeEnumCondition;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Description :
 * @Author : zhangMing
 * @Date : Created in 19:01 2020-09-27
 */
public interface CodeEnumMapper {

    /**
     * 条件查询代码枚举值
     * @param condition
     * @return
     */
    List<EnumBO> getEnumBoListByCondition(CodeEnumCondition condition);

}
