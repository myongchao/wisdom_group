package com.wjy.wisdom.service;

import com.baomidou.mybatisplus.service.IService;
import com.wjy.wisdom.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Juci123
 * @since 2018-11-29
 */
public interface UserService extends IService<User>{

    User selectWithNum(String num);
}
