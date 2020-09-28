package com.fastenum.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * @Description : 工具类
 * @Author : zhangMing
 * @Date : Created in 18:41 2020-09-27
 */
public class CommonUtil {

    /**
     * 处理异常
     * @param bindingResult
     * @return
     */
    public static String processErrorString(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()){
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(  );
        for (FieldError fieldError : bindingResult.getFieldErrors()){
            stringBuilder.append( fieldError.getDefaultMessage() );
        }
        return stringBuilder.subSequence( 0 , stringBuilder.length() - 1 ).toString();
    }

}
