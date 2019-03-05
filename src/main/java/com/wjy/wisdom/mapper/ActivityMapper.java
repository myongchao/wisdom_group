package com.wjy.wisdom.mapper;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wjy.wisdom.entity.Activity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Juci123
 * @since 2019-03-05
 */
public interface ActivityMapper extends BaseMapper<Activity> {

    List<Activity> selectPageWith(Page<Activity> plusPage,@Param("ew") Wrapper<Activity> wrapper);
}
