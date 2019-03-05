package com.wjy.wisdom.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wjy.wisdom.entity.Activity;
import com.baomidou.mybatisplus.service.IService;
import com.wjy.wisdom.entity.Teamclass;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Juci123
 * @since 2019-03-05
 */
public interface ActivityService extends IService<Activity> {

    Page<Activity> selectPageWith(Page<Activity> plusPage, Wrapper<Activity> formToEntityWrapperWithSearch);
}
