package com.wjy.wisdom.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wjy.wisdom.entity.User;
import com.wjy.wisdom.mapper.UserMapper;
import com.wjy.wisdom.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Juci123
 * @since 2018-11-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    /**
    * @description  通过账号获取用户信息
    * @author 马勇超
    * @date 2019/1/14 14:07
    * @param
    * @return
    */
    @Override
    public User selectWithNum(String num) {
        return baseMapper.selectWithNum(num);
    }
}
