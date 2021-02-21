package com.cloud.ext;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 扩展原理：
 *      BeanPostProcessor: bean后置处理器，bean创建对象初始化前后进行拦截工作
 *      BeanFactoryPostProcessor： BeanFactory的后置处理器
 *          在BeanFactory标准初始化之后调用，所有的bean定义已经保存加载到BeanFactory，但bean的实例还未创建
 *
 * BeanFactoryPostProcessor原理：
 *      1.ioc容器创建对象
 *      2.invokeBeanFactoryPostProcessors(beanFactory)
 *          如何找到所有的BeanFactoryPostProcessor并执行它们的方法；
 *              1.直接在BeanFactory中找到所有的类型是BeanFactoryPostProcessor的组件，并执行它们的方法
 *              2.在初始化创建其他组件前面执行
 *  BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor
 *          postProcessBeanDefinitionRegistry();
 *          在所有bean定义信息将要被加载，bean实例还未创建的；
 *          优于BeanFactoryPostProcessor执行；
 *              利用postProcessBeanDefinitionRegistryPostProcessor给容器中再添加一些额外的组件
 *
 *  原理：
 *          1.ioc创建对象
 *          2.refresh()->invokeBeanFactoryPostProcessors(beanFactory)
 *          3.从容器中获取所有的BeanDefinitionRegistryPostProcessor组件。
 *              1.依次触发所有的postProcessBeanDefinitionRegistry()方法
 *              2.再触发postProcessBeanFactory()方法BeanFactoryPostProcessor;
 *          4.再从容器中找到BeanFactoryPostProcessor组件；
 *              然后依次触发postProcessBeanFactory()方法
 *
 * ApplicationListener：监听容器中发布的事件，可用于驱动模型开发
 *      public interface ApplicationListener<E extends ApplicationEvent>
 *          监听ApplicationEvent及其子类下面的事件，
 * 发布事件的步骤：
 *      1.写一个监听器来监听事件(ApplicationEvent及其子类)
 *      2.把监听器加入到容器
 *      3.只要容器中有相关事件的发布，我们就能监听到这个事件了
 *                  如：
 *                  ContextRefreshedEvent 容器刷新完成(所有bean都完成创建)会发布这个事件；
 *                  ContextClosedEvent  关闭容器会发布这个事件；
 *      4.发布事件：
 *          context.publishEvent(new ApplicationEvent(new String("我发布了一个事件!")) {});
 *
 *  原理：
 *         ContextRefreshedEvent、ContextClosedEvent、extTest$1
 *     1. ContextRefreshedEvent事件：
 *          1.容器创建对象，refresh();
 *          2.finishRefresh(); 容器刷新完成,会发布ContextRefreshedEvent事件，然后自己发布的事件，容器关闭事件ContextClosedEvent
 *          3.
 *          【事件发布流程】：
 *              publicEvent(new ContextRefreshedEvent(this));
 *                  1.获取事件的多播器(派发器)：getApplicationEventMulticaster()
 *                  2.multicastEvent派发事件：
 *                  3.获取所有的ApplicationListener；
 *                      for(final ApplicationListener<?> listener : getApplicationListeners(event, type))
 *                      1.如果有executor,可以支持使用executor进行派发；
 *                          Executor executor = getTaskExecutor();
 *                      2.否则、同步的方式直接执行listener方法；invokeListener(listener, event);
 *                      拿到listener回调onApplicationEvent方法;
 *
 *         【事件多播器(派发器)】
 *               1.容器创建对象：refresh
 *               2.initApplicationEventMulticaster():初始化ApplicationEventMulticaster;
 *                      1.先去容器中找有没id=“applicationEventMulticaster”的组件
 *                      2.如果没有则 new SimpleApplicationEventMulticaster(beanFactory);
 *                          并且加入到容器中，我们就可以在其他组件派发事件，自动注入这个初始化ApplicationEventMulticaster；
 *         【容器中有哪些监听器】
 *               1.容器创建对象：refresh
 *               2.registerListeners();
 *                  从容器中拿到所有的监听器，把他们注册到applicationEventMulticaster
 *                  String[] listenerBeanNames = getBeanNamesForType(Application.class,true, false);
 *                  // 将listener注册到容器中
 *                  this.getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);
 *              3.SmartInitializingSingleton的原理： ->afterSingletonsInstantiated()  (及所有的单实例bean创建完成之后调用)
 *                  1.ioc容器创建对象，并refresh
 *                  2.finishBeanFactoryInitialization(beanFactory);初始化剩下的单实例bean
 *                      1.先创建所有的单实例bean；getBean()方法创建
 *                      2.获取所有的单实例bean，判断是否是SmartInitializingSingleton类型的，
 *                          如果是调用afterSingletonsInstantiated();
 *                      然后调用finishRefresh()方法，表示容器创建完成了，
 *                      this.publishEvent((ApplicationEvent)(new ContextRefreshedEvent(this)));
 *                      发布ContextRefreshedEvent容器刷新完成了的事件
 *
 * */
@ComponentScan("com.cloud.ext")
@Configuration
public class ExtConfig {

    @Bean
    public People people(){
        return new People();
    }
}
