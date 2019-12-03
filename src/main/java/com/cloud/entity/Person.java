package com.cloud.entity;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Person {
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        super();
        this.age = age;
        this.name = name;
    }
    public Person(){
        super();
    }

}
