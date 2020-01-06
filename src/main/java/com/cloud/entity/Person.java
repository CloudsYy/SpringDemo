package com.cloud.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@Data
public class Person {
    //这里可以用@Value表达式
    //1、基本数值复制
    //2、SpEL：#{}
    //3、${}
    @Value("张三")
    private String name;//名称
    @Value("#{20-1}")
    private Integer age;//年龄
    @Value("${person.nickName}")
    private String nickName;//昵称

    public Person(String name, Integer age) {//有参构造
        super();
        this.age = age;
        this.name = name;
    }

    public Person(){//无参构造
        super();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
