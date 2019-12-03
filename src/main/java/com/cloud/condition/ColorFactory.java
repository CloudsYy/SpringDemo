package com.cloud.condition;

import com.cloud.entity.Color;
import org.springframework.beans.factory.FactoryBean;

public class ColorFactory implements FactoryBean {
    public Object getObject() throws Exception {
        return new Color();
    }

    public Class<?> getObjectType() {
        return Color.class;
    }

    //控制是否单例
    //true 这个bean是单例，在容器中保存一份
    //false 多实例，每次获取都会创建一个新的bean
    public boolean isSingleton() {
        return true;
    }
}
