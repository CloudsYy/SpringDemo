package com.cloud.condition;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class linuxCondition implements Condition {
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //获取运行时环境
        Environment environment = context.getEnvironment();
        String property = environment.getProperty("os.name");

        //可以判断容器中的bean注册情况，也可以给容器注册bean
        BeanDefinitionRegistry registry = context.getRegistry();

        boolean person = registry.containsBeanDefinition("person");

        System.out.println("person = " + person);

        if (property.contains("linux")) {
            return true;
        }
        return false;
    }

}
