package com.wjy.wisdom.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wjy.wisdom.entity.League;
import com.baomidou.mybatisplus.service.IService;
import com.wjy.wisdom.entity.dto.ImportLeagueDto;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Juci123
 * @since 2019-02-02
 */
public interface LeagueService extends IService<League> {

    Page<League> selectWithLeague(Page<League> plusPage, Wrapper<League> leagueWrapper);

    List<ImportLeagueDto> importDataToDo(List<Map<String, Object>> mapList);

    Page<League> selectWithLeagueWithGanbu(Page<League> plusPage, Wrapper<League> leagueWrapper);
}
