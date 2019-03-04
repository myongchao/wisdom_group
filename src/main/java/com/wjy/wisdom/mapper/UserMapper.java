package com.wjy.wisdom.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wjy.wisdom.entity.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Juci123
 * @since 2018-11-29
 */
public interface UserMapper extends BaseMapper<User> {

    User selectWithNum(String num);
}
