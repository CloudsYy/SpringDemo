package com.cloud.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;

/*
* 切面类
* */
@Aspect
public class Logaspects {
    //抽取公共的切入点
    //1.本类的引用
    //2.其他切面的引用
    //所有方法，两个参数
    //有一点需要注意的，JoinPoint需要在方法的最前面
    @Pointcut("execution(public int com.cloud.aop.object.MathCal.*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        System.out.println(""+joinPoint.getSignature().getName()+"除法运行...参数列表是{"+ Arrays.asList(args) +"}");
    }

    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint){
        System.out.println("除法运行结束...参数列表是{"+joinPoint.getSignature().getName()+"}");
    }

    @AfterReturning(value = "pointCut()",returning = "result")
    public void logReturn(Object result){
        System.out.println("除法运行返回...参数列表是{}");
    }

    @AfterThrowing(value = "pointCut()",throwing = "e")
    public void logThrowing(JoinPoint joinPoint,Exception e){
        System.out.println(joinPoint.getSignature().getName()+"除法方法抛异常...参数列表是{"+e+"}");
    }

//    @Around("pointCut()")
//    public void logAround(){
//        System.out.println("除法运行结束...参数列表是{}");
//    }
}
