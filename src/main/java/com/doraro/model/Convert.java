package com.doraro.model;

import com.doraro.model.entity.BaseModel;
import com.vip.vjtools.vjkit.mapper.BeanMapper;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * 用于类型转换
 * T表示target
 * @author doraro
 */
public abstract class Convert implements Serializable {
    public <T> T convert(Class<T> clazz) {
        try {
            final T instance = clazz.newInstance();
            BeanUtils.copyProperties(this, instance);
            return instance;
        } catch (Exception e) {
            return null;
        }
    }

}
