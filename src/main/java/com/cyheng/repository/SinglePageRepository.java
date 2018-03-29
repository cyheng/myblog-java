package com.cyheng.repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cyheng.model.DO.SinglePage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SinglePageRepository extends BaseMapper<SinglePage> {
    SinglePage getPageByslug(String slug);
}
