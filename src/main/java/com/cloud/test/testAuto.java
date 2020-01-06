package com.cloud.test;

import com.cloud.MainConfigFile.testAutoMainconfig;
import com.cloud.entity.Boss;
import com.cloud.entity.Car;
import com.cloud.entity.Color;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
* 自动装配-方法，构造器的位置自动装配
* */
public class testAuto {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(testAutoMainconfig.class);

    @Test
    public void print(){
        final Car car = applicationContext.getBean(Car.class);
        System.out.println("car = " + car);

        final Boss boss = applicationContext.getBean(Boss.class);
        System.out.println("boss = " + boss);

        final Color color = applicationContext.getBean(Color.class);
        System.out.println("color = " + color);

        final String[] names = applicationContext.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("name = " + name);
        }
    }
}
