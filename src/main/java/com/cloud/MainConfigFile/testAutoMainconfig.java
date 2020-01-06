package com.cloud.MainConfigFile;

import com.cloud.entity.Car;
import com.cloud.entity.Color;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.cloud.controller","com.cloud.dao","com.cloud.Service","com.cloud.entity"})
public class testAutoMainconfig {

    @Bean
    public Car car(){
        return new Car();
    }

    //@Bean标注的方法创建对象的时候，方法参数的值自动从容器中获取,默认不写@Autowired
    @Bean
    public Color color(Car car){
        final Color color = new Color();
        color.setCar(car);
        return color;
    }
}
