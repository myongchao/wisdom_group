package com.wjy.wisdom.mapper;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wjy.wisdom.entity.League;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Juci123
 * @since 2019-02-02
 */
public interface LeagueMapper extends BaseMapper<League> {

    List<League> selectWithLeague(Page<League> plusPage,@Param("ew") Wrapper<League> leagueWrapper);

}
