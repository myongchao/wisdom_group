package com.wjy.wisdom.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wjy.wisdom.entity.Teamclass;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Juci123
 * @since 2019-03-05
 */
public interface TeamclassService extends IService<Teamclass> {

    Page<Teamclass> selectPageWith(Page<Teamclass> page, Wrapper<Teamclass> wrapper);
}
