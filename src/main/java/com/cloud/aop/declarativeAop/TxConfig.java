package com.cloud.aop.declarativeAop;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 声明式事务
 *
 * 环境搭建：
 *      1.导入相关依赖，数据源，数据驱动，spring-jdbc模块
 *      2.配置数据源、JdbcTemplate(spring提供的简化数据库操作的工具)操作数据
 *      3.给方法上加一个@Transactional的注解，则说明了该方法是一个事务方法
 *      4.需要配置@EnableTransactionManagement的注解，开启基于注解的事务管理
 *  注：那代理了某个对象或者类，则调用该类的方法，则methodintercepted会工作，及方法拦截器
 *
 *  原理：
 *  1） @EnableTransactionManagement
 *          利用TransactionManagementConfigurationSelector给容器注册组件
 *          导入两个组件：AutoProxyRegistrar、ProxyTransactionManagementConfiguration
 *  2） AutoProxyRegistrar：
 *          给容器注册InfrastructureAdvisorAutoProxyCreator组件
 *          InfrastructureAdvisorAutoProxyCreator：
 *              利用后置处理器机制创建对象以后，包装对象，返回代理对象（增强器）,代理对象利用拦截器进行调用；
 *  3） ProxyTransactionManagementConfiguration：
 *          1.给容器注册事务增强器：
 *              事务增强器要用事务注解的信息，利用TransactionAttributeSource解析事务注解
 *          2.事务拦截器：
 *              TransactionInterceptor  保存了事务属性信息，事务管理器；
 *              它是methodInterceptor；
 *                在执行目标方法的时候：
 *                          执行拦截器链；
 *                          事务拦截器：
 *                              1.先获取事务相关属性
 *                              2.再获取PlatformTransactionManager,如果事先没有添加指定任何TransactionManager
 *                                  最终会从容器中按照类型获取一个PlatformTransactionManager
 *                              3.执行目标方法：
 *                                  如果正常，利用事务管理器，提交事务
 *                                  如果异常，获取到事务管理器，利用事务管理器进行回滚操作；
 *
 * */
@EnableTransactionManagement
@ComponentScan("com.cloud.aop.declarativeAop")
@Configuration
public class TxConfig {

    @Bean
    public DataSource dataSource(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://120.79.75.136:3306/test?characterEncoding=utf-8&useSSL=false");
            dataSource.setUser("root");
            dataSource.setPassword("Dontlietome");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        // Spring对@Configurable类做了特殊的处理，给容器中加组件的方法，多次调用都只是从容器中找组件，
        // 也就是只会创建一次，不会多次创建！
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }


    // 一定要把事务管理器注册到容器中，也写明事务管理的数据源
    @Bean
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }
}
