package com.doraro.service.impl;

import com.doraro.model.entity.UserLike;
import com.doraro.mapper.UserLikeMapper;
import com.doraro.service.IUserLikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户点赞表 服务实现类
 * </p>
 *
 * @author doraro
 * @since 2019-02-17
 */
@Service
public class UserLikeServiceImpl extends ServiceImpl<UserLikeMapper, UserLike> implements IUserLikeService {

}
