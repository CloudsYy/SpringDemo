package com.cloud.entity;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Dog {
    public Dog(){
        System.out.println("the dog is construct...");
    }

    @PostConstruct
    public void init(){
        System.out.println("dog is init ...");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("dog is destroy ...");
    }


}
