package com.wjy.wisdom.entity.dto;

import com.wjy.wisdom.entity.League;
import com.wjy.wisdom.entity.User;
import lombok.Data;

/**
 * Copyright (C), 2018 - 2019, ZhengZhouChuangZhi. Co., Ltd.
 * FileName: [ 文件名 ]
 * 类的详细说明
 *
 * @author 马勇超
 * @version 1.0
 * @date 2019/1/24$ 10:26$
 */
@Data
public class ImportLeagueDto extends League {
    private String tips;
    private Boolean success;
}
