package com.wjy.wisdom.entity.jsonUtil;

import lombok.Data;

import javax.servlet.http.HttpServletResponse;

/**
 * Copyright (C), 2018 - 2019, ZhengZhouChuangZhi. Co., Ltd.
 * FileName: [ 文件名 ]
 * 类的详细说明
 *
 * @author 马勇超
 * @version 1.0
 * @date 2019/1/11$ 8:58$
 */

public class JsonResultUtils{

//    public static HttpServletResponse success(HttpServletResponse response){
//        Integer code;
//        Boolean success;
//        HttpServletResponse data;
//        JsonResultEntity resultEntity = new JsonResultEntity();
//        resultEntity.setData(response);
//
//    }

    public static JsonResultEntity success(Object object) {
        JsonResultEntity jsonResult = new JsonResultEntity();
        jsonResult.setData(object);
        jsonResult.setCode(200);
        jsonResult.setSuccess(true);
        return jsonResult;
    }

    public static JsonResultEntity success(Object object, Boolean b) {
        JsonResultEntity jsonResult = new JsonResultEntity();
        jsonResult.setData(object);
        if(b){
            jsonResult.setCode(200);
        }
        jsonResult.setSuccess(b);
        return jsonResult;
    }

    public static JsonResultEntity success(Object object, Integer code, Boolean b) {
        JsonResultEntity jsonResult = new JsonResultEntity();
        jsonResult.setData(object);
        jsonResult.setCode(code);
        jsonResult.setSuccess(b);
        return jsonResult;
    }

    public static JsonResultEntity success(Boolean b) {
        JsonResultEntity jsonResult = new JsonResultEntity();
        jsonResult.setData("");
        jsonResult.setCode(200);
        jsonResult.setSuccess(b);
        return jsonResult;
    }

    public static JsonResultEntity error(Integer code, Boolean b) {
        JsonResultEntity jsonResult = new JsonResultEntity();
        jsonResult.setCode(code);
        jsonResult.setSuccess(b);
        return jsonResult;
    }

    public static JsonResultEntity success(Integer code, Boolean b) {
        JsonResultEntity jsonResult = new JsonResultEntity();
        jsonResult.setCode(code);
        jsonResult.setSuccess(b);
        return jsonResult;
    }

}
