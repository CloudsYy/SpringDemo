package com.cloud.condition;

import com.cloud.entity.Rainbow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;


public class MyBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /*
    * AnnotationMetadata 可以获取当前类的注解信息
    * BeanDefinitionRegistry BeanDefinition注册类
    *                        把所需要的添加到容器的bean，调用
    * BeanDefinitionRegistry.registerBeanDefinition手工注册
    * */
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean b = registry.containsBeanDefinition("com.cloud.entity.Red");
        boolean b1 = registry.containsBeanDefinition("com.cloud.entity.Yellow");
        if (b&&b1) {
            //指定Bean定义信息，包括bean的类型，bean的scope(所用域)
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Rainbow.class);
            //注册一个bean，指定bean名
            registry.registerBeanDefinition("Rainbow",rootBeanDefinition);
        }


    }
}
