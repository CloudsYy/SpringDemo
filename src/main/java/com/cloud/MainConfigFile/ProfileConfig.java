package com.cloud.MainConfigFile;

import com.cloud.entity.Red;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;

/*
* Profile：
*    Spring为我们提供了可以根据当前的环境，动态的激活和切换一系列组件的功能
* 开发环境，测试环境，生产环境
* 数据源(/A/B/C)
*
* 1.加了环境标识的bean，只有该环境被激活的时候才能注册到容器中，默认是default环境
* 2.同时也可以写在类上，只有是指定的环境的时候，整个配置类里面的配置才会开始生效
* 3.没有标注环境标识的@Profile在任何环境下都是可以加载的
* */
//@Profile("default")
@PropertySource("classpath:/properties/db.properties")
@Configuration
public class ProfileConfig implements EmbeddedValueResolverAware {

    @Value("${db.user}")
    private String user;

    private StringValueResolver resolver;

    private String driverClass;

//    @Profile("test")
    @Bean
    public Red red(){
        return new Red();
    }

    @Profile("test")
    @Bean("testDataSource")
    public DataSource dataSourceTest(@Value("${db.password}") String pwd) throws Exception{
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://120.79.75.136:3306/hzz");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Profile("dev")
    @Bean("devDataSource")
    public DataSource dataSourceDev(@Value("${db.password}") String pwd) throws Exception{
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://120.79.75.136:3306/hzz");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Profile("prod")
    @Bean("prodDataSource")
    public DataSource dataSourceProd(@Value("${db.password}") String pwd)throws Exception{
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://120.79.75.136:3306/hzz");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        this.resolver = stringValueResolver;
        driverClass = resolver.resolveStringValue("${db.driver}");
    }
}
