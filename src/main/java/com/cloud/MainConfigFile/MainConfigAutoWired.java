package com.cloud.MainConfigFile;

import com.cloud.dao.BookDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan({"com.cloud.controller","com.cloud.dao","com.cloud.Service"})
public class MainConfigAutoWired {

    /*
       自动装配：
       spring利用依赖注入DI，完成对IOC容器中各个组件的依赖关系
     * @Autowired，自动注入
     * 1.默认优先按照类型去容器中找对应的组件:new AnnotationConfigApplicationContext(MainConfigAutoWired.class);进行赋值
     * 2.如果找到多个相同类型的组件，再将属性名称作为相应的组件的id去容器中查找  context.getBean("bookDao");
     * 3.@Qualifier("bookDao"),指定的特定装配的组件的id，而不是使用属性名
     * 4.自动装配默认一定要将属性赋值好，没有就会报错；可以使用autowired(required=false);
     * 5.@Primary：让spring自动装配的时候，默认使用首选的bean名字

       spring还支持使用@Resource(JSR250)和@Inject(JSR330)这两个是Java的规范的注解
       @Resource:
                 可以和@Autowired一样完成自动装配的功能，默认是按照组件的名称进行装配
                 但是没有支持@Primary功能，没有支持@Autowired(required=false)
       @@Inject:
               需要导入javax.inject的包，和@Autowired的功能一样，没有required=false
       @Autowired spring定义的，而这些注解之所以能够应用，是因为有相应的postProcessor来处理的
       @AutowiredAnnotationBeanPostProcessor：解析完成自动装配功能
       1.标注在方法位置上
       2.标注在参数位置上
       3.标注在构造器上

       自定义组件想要使用spring容器中底层定义好的一些组件(ApplicationContext,BeanFactory,xxx)
       自定义组件实现xxxaware，在创建对象的时候，会调用接口规定的方法：aware
       把spring底层定义好的一些组件注入到bean中：
         xxxaware，功能使用xxxProcessor：
            ApplicationContextAware  ==》  ApplicationContextAwareProcessor
     * */
    @Primary
    @Bean("bookDao2")
    public BookDao bookDao(){
        BookDao bookDao = new BookDao();
        bookDao.setLabel("2");
        return bookDao;
    }
}
