package com.fastenum.fenum.service;

import com.fastenum.bo.EnumBO;
import com.fastenum.condition.CodeEnumCondition;
import com.fastenum.einterface.CodeEnumService;
import com.fastenum.fenum.business.CodeEnumBusiness;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description : 代码枚举值接口实现类
 * @Author : zhangMing
 * @Date : Created in 18:49 2020-09-27
 */
@Slf4j
@Service
public class CodeEnumServiceImpl implements CodeEnumService {


    @Resource
    private CodeEnumBusiness codeEnumBusiness;

    /**
     * 根据条件获取枚举值
     * @param condition
     * @return
     */
    @Override
    public List<EnumBO> getEnumBoListByCondition(CodeEnumCondition condition) {
        return codeEnumBusiness.getEnumBoListByCondition(condition);
    }
}
