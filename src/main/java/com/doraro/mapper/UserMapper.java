package com.doraro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.doraro.model.entity.User;
import com.doraro.model.param.UserDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by cyheng on 2018/2/24.
 */
@Repository
public interface UserMapper extends BaseMapper<User> {


    User findUserByName(String username);

    UserDetail findUserById(String id);
}
