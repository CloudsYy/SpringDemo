package com.cloud.aop.test;


import com.cloud.aop.config.mainconfigAop;
import com.cloud.aop.object.MathCal;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class testAspects {

    @Test
    public void test(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(mainconfigAop.class);
        MathCal bean = context.getBean(MathCal.class);
        bean.div(1,1);
        context.close();
    }
}
