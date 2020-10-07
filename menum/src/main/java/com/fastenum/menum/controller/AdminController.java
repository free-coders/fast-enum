package com.fastenum.menum.controller;

import com.fastenum.bo.EnumBO;
import com.fastenum.exception.BusinessException;
import com.fastenum.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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


    /**
     * 登陆接口
     * @param email
     * @param password
     * @return
     */
    @PostMapping( "/login" )
    public String loginpage(@RequestParam( name="email" ) String email ,
                            @RequestParam( name="password") String password) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty( password )){
            //这里从数据库查询  用户名或密码不能为空
            EnumBO errorEnumBo = new EnumBO();
            throw new BusinessException( errorEnumBo );
        }
        if (email.equals( this.email ) && CommonUtil.encodeByMd5( password ).equals( this.encryptPassword )){
            //登陆成功,admin登陆设置全部权限
            httpServletRequest.getSession().setAttribute( CURRENT_ADMIN_SESSION , email );
            //跳转到首页
            return "redirect:/admin/admin/index";
        }else{
            log.info( "密码为:{}" , CommonUtil.encodeByMd5( password ) );
            //登陆失败 用户名或密码不能为空
            EnumBO errorEnumBo = new EnumBO();
            throw new BusinessException( errorEnumBo );
        }

    }


}


