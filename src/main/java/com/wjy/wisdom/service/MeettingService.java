package com.wjy.wisdom.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wjy.wisdom.entity.Meetting;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Juci123
 * @since 2019-03-05
 */
public interface MeettingService extends IService<Meetting> {

    Page<Meetting> selectPageWith(Page<Meetting> page, Wrapper<Meetting> wrapper);
}
