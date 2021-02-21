package com.cloud.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;


@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryPostProcessor.....will be starting........");
        int count = beanFactory.getBeanDefinitionCount();
        System.out.println("当前有"+count+"个bean.");
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (int i = 0; i < count; i++) {
            System.out.println("当前是第"+i+"个bean，bean名称为："+beanDefinitionNames[i]);
        }

    }
}
