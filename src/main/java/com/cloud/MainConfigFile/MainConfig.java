package com.cloud.MainConfigFile;

import com.cloud.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:/properties/PProperties.properties"})
public class MainConfig {

    @Bean
    public Person person(){
        return new Person();
    }
}
