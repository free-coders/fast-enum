package com.fastenum.response;

import lombok.Data;

/**
 * @Description : 数据统一返回格式
 * @Author : zhangMing
 * @Date : Created in 17:57 2020-09-27
 */
@Data
public class ResponseData {

    /**
     * 表明读经请求的返回处理结果，"success"或"fail"
     */
    private String status;

    /**
     * 若status=success时，表明对应的返回的json类数据
     * 若status=fail时，则data内将使用通用的错误码对应的格式
     */
    private Object data;

    /**
     * 创建返回结果
     * @param result
     * @return
     */
    public static ResponseData create(Object result) {
        return ResponseData.create( result, "success" );
    }

    /**
     * 创建返回结果
     * @param result
     * @param status
     * @return
     */
    public static ResponseData create(Object result, String status) {
        ResponseData responseData = new ResponseData();
        responseData.setStatus( status );
        responseData.setData( result );
        return responseData;
    }

}
