package com.cloud.test;

import com.cloud.MainConfigFile.ProfileConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

public class testDataSource {

    @Test
    public void test(){
        //1.创建容器
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //2.调用无参数构造方法,激活环境，设置相应的环境变量参数
        context.getEnvironment().setActiveProfiles("dev");
        //注册主配置类
        context.register(ProfileConfig.class);
        //启动刷新容器
        context.refresh();

        final String[] beanNamesForType = context.getBeanNamesForType(DataSource.class);
        for (String string : beanNamesForType) {
            System.out.println("string = " + string);
        }
        final String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("name = " + name);
        }

    }
}
