package com.fastenum.fenum.service;

import com.fastenum.bo.EnumBO;
import com.fastenum.condition.EnumCondition;
import com.fastenum.fservice.EnumService;
import com.fastenum.fenum.business.EnumBusiness;
import com.fastenum.fenum.redis.EnumConfig;
import com.fastenum.fenum.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description : 代码枚举值接口实现类
 * @Author : zhangMing
 * @Date : Created in 18:49 2020-09-27
 */
@Slf4j
@Service
public class EnumServiceImpl implements EnumService {


    @Resource
    private EnumBusiness cenumBusiness;

    @Resource
    private RedisService redisService;

    /**
     * 根据条件获取枚举值
     * @param condition
     * @return
     */
    @Override
    public List<EnumBO> getEnumBoListByCondition(EnumCondition condition) {
        List<EnumBO> enumBOList = new ArrayList<>(  );
        if (StringUtils.isNotEmpty( condition.getCodeId() ) && StringUtils.isNotEmpty( condition.getEnumId() )){
            //查询单个枚举值
            EnumBO enumBO = redisService.getOfMap( EnumConfig.fcode , condition.getCodeId() , condition.getEnumId() , EnumBO.class );
            if (null == enumBO){
                List<EnumBO> dbResult = cenumBusiness.getEnumBoListByCondition(condition);
                if (null != dbResult && dbResult.size() == 1){
                    enumBO = dbResult.get( 0 );
                    redisService.setOfMap( EnumConfig.fcode , enumBO.getCodeId() , enumBO.getEnumId() , enumBO );
                }else {
                    //回种空值。
                    redisService.setOfMap( EnumConfig.fcode , condition.getCodeId() , condition.getEnumId() , "" );
                }
            }
            if (null != enumBO){
                enumBOList.add( enumBO );
            }
        }else if (StringUtils.isNotEmpty( condition.getCodeId() )){
            //获取全部代码枚举值
            Map<String,EnumBO> map = redisService.getAllOfMap( EnumConfig.fcode , condition.getCodeId() , EnumBO.class );
            if (null == map){
                //从数据库中获取
                enumBOList = cenumBusiness.getEnumBoListByCondition(condition);
                //todo 这里也需要进行回种空值。

                //写入redis中
                for (EnumBO enumBO : enumBOList){
                    redisService.setOfMap( EnumConfig.fcode , enumBO.getCodeId() , enumBO.getEnumId() , EnumBO.class );
                }
            }else {
                for (String str : map.keySet()){
                    enumBOList.add( map.get( str ) );
                }
            }
        }
        return enumBOList;
    }
}
