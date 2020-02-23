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
*           在spring中有很多EnableXXX  来开启某个功能;
*   AOP原理：@EnableAspectJAutoProxy；【给容器中注册了什么组件，这个组件什么时候工作，这个组件的功能是什么？】
*       1.@EnableAspectJAutoProxy是什么？
*           @Import(AspectJAutoProxyRegistrar.class):给容器中导入AspectJAutoProxyRegistrar
*               利用AspectJAutoProxyRegistrar自定义给容器中注册bean：beanDefinetion 用来保存bean的定义信息
*               internalAutoProxyCreator=AnnotationAwareAspectJAutoProxyCreator
*
*            给容器中注册一个AnnotationAwareAspectJAutoProxyCreator；
*       2.AnnotationAwareAspectJAutoProxyCreator：
*           AnnotationAwareAspectJAutoProxyCreator
*               ->AspectJAwareAdvisorAutoProxyCreator
*                   ->AbstractAdvisorAutoProxyCreator
*                       ->AbstractAutoProxyCreator
*                           (实现了)implements SmartInstantiationAware【BeanPostProcessor】, BeanFactoryAware
*                           ->ProxyProcessorSupport
*                           关注后置处理器（在bean初始化前后做的事情）、自动装配beanFactory
*          AbstractAutoProxyCreator.setBeanFactory()
*          AbstractAutoProxyCreator.有后置处理器的逻辑
*
*          AbstractAdvisorAutoProxyCreator.setBeanFactory()->initBeanFactory()
*          AnnotationAwareAspectJAutoProxyCreator.initBeanFactory()
*
*          流程：
*          1、传入配置类、创建IOC容器
*          2、注册配置类、调用refresh()刷新容器；
*          3、registerBeanPostProcessors(beanFactory);注册bean的后置处理器来方便拦截bean的创建
*             1、先获取ioc容器中已经定义了的需要创建对象的所有beanPostProcessor
*             2、给容器中添加别的beanPostProcessor
*             3、优先注册实现了PriorityOrdered接口的BeanPostProcessor
*             4、再在容器中注册了Ordered接口的BeanPostProcessor
*             5、注册没实现优先级接口的BeanPostProcessor
*             6、注册beanPostProcessor，实际上就是创建BeanPostProcessor对象，保存在容器中
*               创建internalAutoProxyCreator的BeanPostProcessor【AnnotationAwareAspectJAutoProxyCreator】
*                   1、创建bean的实例
*                   2、populateBean；给bean的各种属性赋值
*                   3、initializeBean：初始化bean
*                       1、invokeAwareMethods(),处理Aware接口的方法回调
*                       2、applyBeanPostProcessorBeforeInitialization 应用后置处理器的postProcessorBeforeInitialization()
*                       3、invokeInitMethods() 执行初始化方法
*                       4、applyBeanPostProcessorAfterInitialization 应用后置处理器的postProcessorAfterInitialization()
*                   4、beanPostProcessor（AnnotationAwareAspectJAutoProxyCreator）创建成功；aspectJAdvisorsBuilder
*              7、把beanPostProcessor注册到beanFactory中
*                       beanFactory.addBeanPostProcessor(postProcessor);
*             ====以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程=======
*                   AnnotationAwareAspectJAutoProxyCreator => InstantiationAwareBeanPostProcessor  (实例化beanPostProcessor)
*            4、finishBeanFactoryInitialization(beanFactory);完成BeanFactory初始化工作，创建剩下的单实例bean
*                   1、遍历获取容器中所有的bean，依次创建对象getbean(beanName);
*                       getBean->doGetBean()->getSingleton()
*                   2、创建bean
*                       【AnnotationAwareAspectJAutoProxyCreator在所有bean创建之前会有一个拦截InstantiationAwareBeanBeanPostProcessor，
*                        会调用applyBeanPostProcessorBeforeInstantiation()】
*                       1、先从缓存中获取当前bean，如果能获取到，说明bean是之前被创建过的，直接使用，否则再创建
*                           只要创建好的bean都会被缓存起来；
*                       2、createBean();[创建bean] AnnotationAwareAspectJAutoProxyCreator会在任何bean创建之前先尝试返回bean实例
*                           【BeanPostProcessor是再Bean对象创建完成初始化前后调用的】
*                           【InstantiationAwareBeanBeanPostProcessor是在创建Bean实例之前先尝试后置处理器返回对象的】
*                           1、resolveBeforeInstantiation(beanName,mbdToUse);解析BeforeInstantiation
*                               希望后置处理器在此能返回一个代理对象，如果能返回代理对象就使用，如果不能就继续
*                               1、后置处理器先尝试返回对象；
*                               bean = applyBeanPostProcessorBeforeInstantiation();
*                                    拿到所有的后置处理器，如果是InstantiationAwareBeanBeanPostProcessor
*                                    就执行postProcessorBeforeInstantiation
*                               if(bean != null){...}
*                           2、doCreateBean(beanName,mbdToUse,args);真正的去创建一个bean实例，和3.6的流程一样。
*                           3、
*
*             AnnotationAwareAspectJAutoProxyCreator[InstantiationAwareBeanBeanPostProcessor]的作用：
*             1、每一个bean创建之前，调用postProcessorBeforeInstantiation();
*               关心MathCald和LogAspect的创建
*               1、判断但概念bean是否在advisedBean中(保存了所有需要增强的bean)
*               2、判断当前bean是否是基础类型的Advice，Pointcut、Advisor、AopInfrastructureBean,etc
*                  或者是否是切面也就是有@Aspect的注解
*               3、是否需要跳过
*                   1、获取候选的增强器（切面里的通知方法）
*                       每一个封装的通知方法的增强器是InstantiationModelAmarePointcutAdvisor;
*                       判断每一个增强器是否是AspectJPointcutAdvisor类型：返回true
*                   2、永远返回false
*      2、创建对象
*      postProcessorAfterInitialization
*       return wrapIfNecessary(bean,beanName,cacheKey); //包装如果需要的情况下
*           1、获取当前bean的所有增强器(就是通知方法)
*               1、找到能在当前bean使用的增强器(找到哪些通知方法是需要切入当前bean的方法的)
*               2、获取到能在bean使用的增强器
*               3、给增强其排序
*           2、保存当前bean在advisedBeans中；
*           3、如果当前bean需要增强，创建当前bean的代理对象；
*               1、获取所有增强器
*               2、报错到proxyFactory
*               3、创建代理对象：spring自动决定
*                   JdkDynamicAopProxy(config);jdk动态代理
*                   ObjenesisCglibAopProxy(config);cglib的动态代理；（当然我们也可以强制使用cglib动态代理）
*           4、给容器中返回当前组件使用cglib组件增强了的代理对象
*           5、以后容器中获取到的就是这个组件的代理对象，执行目标方法的时候，代理对象就会执行通知方法的流程
*
* */

@EnableAspectJAutoProxy
@Configuration
public class mainconfigAop {

    //业务逻辑类加入容器中
    @Bean
    public Logaspects logaspects(){
        return new Logaspects();
    }

    //切面类加入到容器中
    @Bean
    public MathCal mathCal(){
        return new MathCal();
    }
}
