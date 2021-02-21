package com.cloud.ext;

import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class extTest {

    @Test
    public void test(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExtConfig.class);

        // 发布事件
        context.publishEvent(new ApplicationEvent(new String("我发布了一个事件!")) {
        });
        context.close();
    }
}
