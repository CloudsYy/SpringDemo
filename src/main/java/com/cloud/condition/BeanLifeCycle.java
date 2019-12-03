package com.cloud.condition;

import com.cloud.entity.Black;
import com.cloud.entity.White;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("com.cloud")
@Configuration
public class BeanLifeCycle {
    /*
    * bean的生命周期：
    * bean的创建---初始化---销毁过程
    * 容器管理着bean的生命周期
    * 我们可以自定义初始化和销毁方法，容器在bean进行前
    *
    * 构造(对象创建)
    * 1) 单实例：在容器启动时，创建的对象
    * 2) 多实例：在每次获取的时候创建对象
    *BeanPostProcessor.postProcessBeforeInitialization
    * 初始化：
    *      对象创建完，并赋值好，调用初始化方法、
    *BeanPostProcessor.postProcessAfterInitialization
    * 销毁时：
    *      容器关闭的时候
    *
    *      可以通过@bean指定init-method和destroy-method，
    *      通过让InitializingBean 初始化逻辑
    *            DisposableBean  销毁逻辑
    *      可以使用JSR250:
    *           @PostConstruct 在bean创建完成并赋值完成，来执行初始化方法
    *           @PreDestroy  在容器执行销毁之前，通知我们进行清理工作
    *      @BeanProcessor bean的后置处理器
    *                     在bean初始化之前处理工作
    *
    * postProcessBeforeInitialization 在初始化工作之前
    * postProcessAfterInitialization 在初始化工作之后
    *遍历得到容器中所有的BeanPostProcessor；挨个执行beforeInitialization，
    * 一旦返回null，直接跳出for循环，遍历得到容器中所有的BeanPostProcessor.postProcessorsBeforeInitialization
    *
    * BeanPostProcessor的原理
    * populateBean(beanName,mbd,instanceWrapper);给bean属性赋值的
    * initializeBean
    * {
    * applyBeanPostProcessorsBeforeInitialization()
    * invokeInitMethods(beanName,WrappedBean, mbd);执行自定义初始化方法
    * applyBeanPostProcessorsAfterInitialization()
    * }
    * Spring底层对BeanPostProcessor的使用；
    *       bean赋值：注入其他组件，@Autowired,生命周期的注解功能，@Async,xxx,BeanPostProcessor;
    * */

    @Bean(initMethod = "init",destroyMethod = "destroy")
    public Black getBlack(){
        return new Black();
    }


    @Bean
    public White getWhite(){
        return new White();
    }
}
