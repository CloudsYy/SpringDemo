package com.cloud.condition;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class windowsCondition implements Condition {
    /*
    * conditionContext 判断条件能使用的上下文环境
    * AnnotatedTypeMetadata 当前注释信息
    * */
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //是否window系统
        //能否获取到ioc使用的beanfactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        //获取类加载器
        ClassLoader classLoader = context.getClassLoader();
        //获取运行时环境
        Environment environment = context.getEnvironment();
        //获取到bean定义注册的类
        BeanDefinitionRegistry registry = context.getRegistry();

        String property = environment.getProperty("os.name");

        if (property.contains("Windows")) {
            return true;
        }

        return false;
    }
}
