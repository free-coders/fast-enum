package com.fastenum.menum.controller;

import com.fastenum.bo.EnumBO;
import com.fastenum.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description : 登陆控制层
 * @Author : zhangMing
 * @Date : Created in 19:07 2020-10-06
 */
@RestController
@RequestMapping("admin")
@Slf4j
public class AdminController {

    @Autowired
    private HttpServletRequest httpServletRequest;

    public static final String CURRENT_ADMIN_SESSION = "currentAdminSession";

    @Value( "${admin.email}" )
    private String email;

    @Value( "${admin.encryptPassword}" )
    private String encryptPassword;


    @RequestMapping(value="/login" , method=RequestMethod.POST)
    public String loginpage(@RequestParam( name="email" ) String email ,
                            @RequestParam( name="password") String password) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty( password )){
            //这里从数据库查询  用户名或密码不能为空
            EnumBO errorEnumBo = new EnumBO();
            throw new BusinessException( errorEnumBo );
        }
        if (email.equals( this.email ) && encodeByMd5( password ).equals( this.encryptPassword )){
            //登陆成功,admin登陆设置全部权限
            httpServletRequest.getSession().setAttribute( CURRENT_ADMIN_SESSION , email );
            //跳转到首页
            return "redirect:/admin/admin/index";
        }else{
            log.info( "密码为:{}" , encodeByMd5( password ) );
            //登陆失败 用户名或密码不能为空
            EnumBO errorEnumBo = new EnumBO();
            throw new BusinessException( errorEnumBo );
        }

    }

    /**
     * md5加密
     * @param str
     * @return
     */
    private String encodeByMd5(String str) {
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


