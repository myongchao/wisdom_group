package com.wjy.wisdom.mapper;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wjy.wisdom.entity.Meetting;
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
public interface MeettingMapper extends BaseMapper<Meetting> {

    List<Meetting> selectPageWith(Page<Meetting> page,@Param("ew") Wrapper<Meetting> wrapper);
}
