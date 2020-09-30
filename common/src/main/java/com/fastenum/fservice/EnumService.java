package com.fastenum.fservice;

import com.fastenum.bo.EnumBO;
import com.fastenum.condition.EnumCondition;

import java.util.List;

/**
 * @Description : 代码枚举值接口类
 * @Author : zhangMing
 * @Date : Created in 18:48 2020-09-27
 */
public interface EnumService {

    /**
     * 根据条件获取枚举值
     * @param condition
     * @return
     */
    public List<EnumBO> getEnumBoListByCondition(EnumCondition condition);

}
