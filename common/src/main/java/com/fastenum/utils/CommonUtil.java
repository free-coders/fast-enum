package com.fastenum.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description : 工具类
 * @Author : zhangMing
 * @Date : Created in 18:41 2020-09-27
 */
@Slf4j
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


    /**
     * md5加密
     * @param str
     * @return
     */
    public static String encodeByMd5(String str) {
        String result = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance( "MD5" );
            BASE64Encoder base64Encoder = new BASE64Encoder();
            result = base64Encoder.encode( messageDigest.digest( str.getBytes("utf-8")) );
        } catch (NoSuchAlgorithmException e) {
            //获取DM5示例失败
            log.info( "获取DM5示例失败" );
        } catch (UnsupportedEncodingException e) {
            log.info( "转换utf-8字节失败" );
        }
        return result;
    }

}
