package com.wjy.wisdom.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wjy.wisdom.entity.Meetting;
import com.wjy.wisdom.entity.Teamclass;
import com.wjy.wisdom.entity.jsonUtil.JsonResultEntity;
import com.wjy.wisdom.entity.jsonUtil.JsonResultUtils;
import com.wjy.wisdom.mian.entity.CommonSearchDto;
import com.wjy.wisdom.service.TeamclassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Juci123
 * @since 2019-03-05
 */
@RestController
@RequestMapping("/teamclass")
@Api(description = "团课")
public class TeamclassController implements Serializable {

    @Autowired
    private TeamclassService service;

    @ApiOperation(value = "分页")
    @PostMapping(value = "page")
    public JsonResultEntity page(@RequestBody CommonSearchDto<Teamclass> searchDto) throws JSONException {
        Page<Teamclass> page = service.selectPageWith(searchDto.getPlusPage(),searchDto.formToEntityWrapperWithSearch(new String[]{""},null));
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
    public JsonResultEntity add(@RequestBody Teamclass teamclass){
        JsonResultEntity resultEntity = JsonResultUtils.success(service.insert(teamclass));
        return resultEntity;
    }

    @ApiOperation(value = "团课列表")
    @GetMapping(value = "list")
    public JsonResultEntity meetList(){
        List<Teamclass> teamclasses = service.selectList(new EntityWrapper<>());
        JsonResultEntity resultEntity = JsonResultUtils.success(teamclasses);
        return resultEntity;
    }
}

