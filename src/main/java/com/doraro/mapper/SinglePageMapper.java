package com.doraro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.doraro.model.entity.SinglePage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface SinglePageMapper extends BaseMapper<SinglePage> {
    SinglePage getPageByslug(String slug);
}
