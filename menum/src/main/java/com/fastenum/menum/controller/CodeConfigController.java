package com.fastenum.menum.controller;

import com.fastenum.condition.EnumCondition;
import com.fastenum.finterface.FEnum;
import com.fastenum.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description : 管理模块demo
 * @Author : zhangMing
 * @Date : Created in 19:30 2020-09-29
 */
@RestController
@RequestMapping("/codeConfig")
public class CodeConfigController {

    @Autowired
    private FEnum fEnum;

    /**
     * 远程调用demo
     * @return
     */
    @GetMapping("/add")
    public ResponseData power(){
        Map<String,Object> map = new HashMap<>(  );
        EnumCondition enumCondition = new EnumCondition();
        enumCondition.setCodeId( "PARAMs" );
        ResponseData responseData = fEnum.getCodeEnumsByCodeId(enumCondition);
        return ResponseData.create( responseData.getData() );
    }
}
