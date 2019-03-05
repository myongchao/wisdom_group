package com.wjy.wisdom.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.wjy.wisdom.entity.Meetting;
import com.wjy.wisdom.entity.jsonUtil.JsonResultEntity;
import com.wjy.wisdom.entity.jsonUtil.JsonResultUtils;
import com.wjy.wisdom.mian.entity.CommonSearchDto;
import com.wjy.wisdom.service.MeettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/meetting")
@Api(description = "团员会议")
public class MeettingController implements Serializable {

    @Autowired
    private MeettingService service;

    @ApiOperation(value = "分页")
    @PostMapping(value = "page")
    public JsonResultEntity page(@RequestBody CommonSearchDto<Meetting> searchDto) throws JSONException {
        Page<Meetting> page = service.selectPageWith(searchDto.getPlusPage(),searchDto.formToEntityWrapperWithSearch(new String[]{"type"},null));
        JsonResultEntity resultEntity = JsonResultUtils.success(page);
        return resultEntity;
    }

}

