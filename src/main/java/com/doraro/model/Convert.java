package com.doraro.model;

import com.vip.vjtools.vjkit.mapper.BeanMapper;

import java.io.Serializable;

/**
 * 用于类型转换
 *
 * @author doraro
 */
public abstract class Convert implements Serializable {
    public <T> T convert(Class<T> clazz) {
        return BeanMapper.map(this, clazz);
    }
}
