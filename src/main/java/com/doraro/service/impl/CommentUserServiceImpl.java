package com.doraro.service.impl;

import com.doraro.model.entity.CommentUser;
import com.doraro.mapper.CommentUserMapper;
import com.doraro.service.ICommentUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author doraro
 * @since 2019-02-17
 */
@Service
public class CommentUserServiceImpl extends ServiceImpl<CommentUserMapper, CommentUser> implements ICommentUserService {

}
