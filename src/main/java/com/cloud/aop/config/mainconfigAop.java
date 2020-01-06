package com.cloud.aop.config;

import com.cloud.aop.aspects.Logaspects;
import com.cloud.aop.object.MathCal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/*
* AOP : 动态代理
* 指在程序运行期间动态的将某行代码切入到指定方法位置进行运行的编程方式；
* 1.导入aop模块，spring aop (spring-aspect)
* 2.定义一个逻辑类(mathCalculator):在业务逻辑运行的时候将日志进行打印（方法之前，方法运行结束，方法出现异常等等）
*      通知方法：
*          前置通知：(@Before) 在目标方法执行前运行
*          后置通知：(@After) 在目标方法执行后运行 （无论是正常结束还是异常结束都执行）
*          返回通知：(@AfterReturning) 在目标返回之后执行
*          异常通知：(@AfterThrowing) 在目标方法抛出异常之后执行
*          环绕通知；(Around) 动态代理 手动推进目标方法执行(jointpoint.procced())
*  将切面类和业务逻辑类(目标方法所在类)都加入到容器中
*  给切面类的方法标注时何时何地运行(通知注解)
*  必须告诉容器哪个是切面类 故用@Aspect
*  给配置类加上@EnableAspectJAutoProxy【开启基于注解的aop模式】
*  在spring中有很多EnableXXX  来开启某个功能
* */

@EnableAspectJAutoProxy
@Configuration
public class mainconfigAop {

    @Bean
    public Logaspects logaspects(){
        return new Logaspects();
    }

    @Bean
    public MathCal mathCal(){
        return new MathCal();
    }
}
