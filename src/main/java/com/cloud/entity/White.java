package com.cloud.entity;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class White implements InitializingBean, DisposableBean {
    public White (){
        System.out.println("the white is construct...");
    }

    public void destroy() throws Exception {
        System.out.println("the white is destroy...");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("the white is afterPropertiesSet...");
    }
}
