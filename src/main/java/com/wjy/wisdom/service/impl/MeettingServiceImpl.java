package com.wjy.wisdom.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wjy.wisdom.entity.Meetting;
import com.wjy.wisdom.mapper.MeettingMapper;
import com.wjy.wisdom.service.MeettingService;
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
public class MeettingServiceImpl extends ServiceImpl<MeettingMapper, Meetting> implements MeettingService {

    @Override
    public Page<Meetting> selectPageWith(Page<Meetting> page, Wrapper<Meetting> wrapper) {
        List<Meetting> meettings = baseMapper.selectPageWith(page,wrapper.eq("isDeleted",0));
        page.setRecords(meettings);
        return page;
    }
}
