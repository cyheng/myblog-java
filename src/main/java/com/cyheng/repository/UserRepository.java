package com.cyheng.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cyheng.model.DO.User;
import com.cyheng.model.VO.UserDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by cyheng on 2018/2/24.
 */
@Mapper
@Repository
public interface UserRepository extends BaseMapper<User> {


    User findUserByName(String username);

    UserDetail findUserById(String id);
}
