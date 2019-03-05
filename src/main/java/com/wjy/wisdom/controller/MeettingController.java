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

    @ApiOperation(value = "删除")
    @PostMapping(value = "del")
    public JsonResultEntity del(@RequestParam("id") Long id){
        JsonResultEntity resultEntity = JsonResultUtils.success(service.deleteById(id));
        return resultEntity;
    }

    @ApiOperation(value = "添加")
    @PostMapping(value = "add")
    public JsonResultEntity add(@RequestBody Meetting meetting){
        if(meetting.getTitle().equals("支部团员大会")) {
            meetting.setType(1);
        }
        if(meetting.getTitle().equals("支部委员会大会")) {
            meetting.setType(2);
        }
        if(meetting.getTitle().equals("团小组会")) {
            meetting.setType(3);
        }
        JsonResultEntity resultEntity = JsonResultUtils.success(service.insert(meetting));
        return resultEntity;
    }

}

