package com.wjy.wisdom.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wjy.wisdom.entity.Teamclass;
import com.wjy.wisdom.mapper.TeamclassMapper;
import com.wjy.wisdom.service.TeamclassService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Juci123
 * @since 2019-03-05
 */
@Service
public class TeamclassServiceImpl extends ServiceImpl<TeamclassMapper, Teamclass> implements TeamclassService {

    @Override
    public Page<Teamclass> selectPageWith(Page<Teamclass> page, Wrapper<Teamclass> wrapper) {
        List<Teamclass> list = baseMapper.selectPageWith(page,wrapper.eq("isDeleted",0));
        page.setRecords(list);
        return page;
    }
}
