package com.wjy.wisdom.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wjy.wisdom.entity.Activity;
import com.wjy.wisdom.entity.Teamclass;
import com.wjy.wisdom.mapper.ActivityMapper;
import com.wjy.wisdom.service.ActivityService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

    @Override
    public Page<Activity> selectPageWith(Page<Activity> plusPage, Wrapper<Activity> formToEntityWrapperWithSearch) {
        List<Activity> list = baseMapper.selectPageWith(plusPage,formToEntityWrapperWithSearch.eq("isDeleted",0));
        plusPage.setRecords(list);
        return plusPage;
    }
}
