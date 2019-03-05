package com.wjy.wisdom.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.wjy.wisdom.entity.Activity;
import com.wjy.wisdom.entity.Teamclass;
import com.wjy.wisdom.entity.jsonUtil.JsonResultEntity;
import com.wjy.wisdom.entity.jsonUtil.JsonResultUtils;
import com.wjy.wisdom.mian.entity.CommonSearchDto;
import com.wjy.wisdom.service.ActivityService;
import com.wjy.wisdom.service.TeamclassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.io.Serializable;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Juci123
 * @since 2019-03-05
 */
@RestController
@RequestMapping("/activity")
@Api(description = "团日活动")
public class ActivityController implements Serializable {

    @Autowired
    private ActivityService service;

    @ApiOperation(value = "分页")
    @PostMapping(value = "page")
    public JsonResultEntity page(@RequestBody CommonSearchDto<Activity> searchDto) throws JSONException {
        Page<Activity> page = service.selectPageWith(searchDto.getPlusPage(),searchDto.formToEntityWrapperWithSearch(new String[]{""},null));
        JsonResultEntity resultEntity = JsonResultUtils.success(page);
        return resultEntity;
    }

    @ApiOperation(value = "删除")
    @PostMapping(value = "del")
    public JsonResultEntity del(@RequestParam("id") Long id){
        JsonResultEntity resultEntity = JsonResultUtils.success(service.deleteById(id));
        return resultEntity;
    }

    @ApiOperation(value = "添加")
    @PostMapping(value = "add")
    public JsonResultEntity add(@RequestBody Activity activity){
        JsonResultEntity resultEntity = JsonResultUtils.success(service.insert(activity));
        return resultEntity;
    }

}

