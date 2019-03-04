package com.wjy.wisdom.entity.dto;

import com.wjy.wisdom.entity.League;
import com.wjy.wisdom.entity.User;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

/**
 * Copyright (C), 2018 - 2019, ZhengZhouChuangZhi. Co., Ltd.
 * FileName: [ 文件名 ]
 * 类的详细说明
 *
 * @author 马勇超
 * @version 1.0
 * @date 2019/1/24$ 10:39$
 */
@Data
public class ListLeagueDto {
    @Valid
    private List<League> data;
}
