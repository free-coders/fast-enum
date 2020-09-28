package com.fastenum.fenum.business;

import com.fastenum.bo.EnumBO;
import com.fastenum.condition.CodeEnumCondition;
import com.fastenum.fenum.mybatis.CodeEnumMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description :
 * @Author : zhangMing
 * @Date : Created in 19:11 2020-09-27
 */
@Component
@Slf4j
public class CodeEnumBusiness {

    @Resource
    private CodeEnumMapper codeEnumMapper;

    /**
     * 根据条件获取枚举值
     * @param condition
     * @return
     */
    public List<EnumBO> getEnumBoListByCondition(CodeEnumCondition condition) {
        return codeEnumMapper.getEnumBoListByCondition(condition);
    }


}
