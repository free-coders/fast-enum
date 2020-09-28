package com.fastenum.fenum.controller;

import com.fastenum.bo.EnumBO;
import com.fastenum.condition.CodeEnumCondition;
import com.fastenum.einterface.CodeEnumService;
import com.fastenum.exception.BusinessException;
import com.fastenum.response.ResponseData;
import com.fastenum.utils.CommonUtil;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @Description : 代码枚举值控制层
 * @Author : zhangMing
 * @Date : Created in 18:23 2020-09-27
 */
@RestController
@RequestMapping("/code")
public class CodeEnumController {

    @Resource
    private CodeEnumService codeEnumService;

    /**
     * 根据代码编号获取枚举值
     * @param condition
     * @return
     */
    @GetMapping("/code_enum_by_condititon")
    public ResponseData getCodeEnumsByCodeId(@Valid CodeEnumCondition condition ,
                                             BindingResult bindingResult){
        //判断参数是否正确
        if (bindingResult.hasErrors()){
            //查询出，异常的枚举值。
            CodeEnumCondition codeEnumCondition = new CodeEnumCondition("ERROR_CODE","PARAM");
            List<EnumBO> enumBOList = codeEnumService.getEnumBoListByCondition( codeEnumCondition );
            EnumBO enumBO = null;
            if (null != enumBOList && enumBOList.size() == 1){
                enumBO = enumBOList.get( 0 );
            }
            throw new BusinessException( enumBO , CommonUtil.processErrorString(bindingResult) );
        }

        return ResponseData.create( null );
    }


}
