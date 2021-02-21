package com.cloud.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.stereotype.Component;


@Component
public class MyBeanDefinationRegistry implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("MyBeanDefinationRegistry....bean的数量"+registry.getBeanDefinitionCount());
        // 两种实例化bean的方式
//        RootBeanDefinition bean = new RootBeanDefinition(People.class);
        AbstractBeanDefinition bean = BeanDefinitionBuilder.rootBeanDefinition(People.class).getBeanDefinition();
        registry.registerBeanDefinition("hello",bean);
    }

    // bean的信息保存中心，以后BeanFactory就是按照BeanDefinitionRegistry里面保存的每一个bean的定义信息，创建实例
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("MyBeanDefinationRegistry....bean的数量"+configurableListableBeanFactory.getBeanDefinitionCount());

    }
}
