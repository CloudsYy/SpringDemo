package com.cloud.aop.declarativeAop;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class TxTest {

    @Test
    public void testTx(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TxConfig.class);
        UserService service = context.getBean(UserService.class);
        service.insert();
        context.close();
    }
}
