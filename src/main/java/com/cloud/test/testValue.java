package com.cloud.test;

import com.cloud.MainConfigFile.MainConfig;
import com.cloud.entity.Person;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class testValue {

    AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(MainConfig.class);

    //如果pom.xml scope为test，则只作用于test的文件夹
    @Test
    public void printBean() {
       getBean(configApplicationContext);
       Person person = (Person) configApplicationContext.getBean("person");
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
//        String[] names = context.getBeanDefinitionNames();
//        for (String name : names) {
//            System.out.println("name = " + name);
//        }
//        ConfigurableEnvironment environment = context.getEnvironment();
//        System.out.println("environment = " + environment.getProperty("person.nickName"));
        System.out.println(person);
    }

    public void getBean(AnnotationConfigApplicationContext configApplicationContext){
        //获取定义的bean名称
        String[] beanDefinitionNames = configApplicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }
    }
}
