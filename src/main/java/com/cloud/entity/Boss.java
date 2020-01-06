package com.cloud.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@Data
//@ToString
public class Boss {//默认加载ioc容器中的组件，容器启动会调用无参构造器创建对象，再进行初始化操作等操作

//    @Autowired
    private Car car;

    //标注在构造器，构造器中的方法参数也是从ioc容器中获取
    //如果当前类只有一个有参构造器，这个有参构造器参数位置的@Autowired可以省略，参数组件依然可以从容器中获取
//    @Autowired
    public Boss(@Autowired Car car){
        this.car = car;
        System.out.println("有参构造器的car = " + car);
    }

    public Car getCar() {
        return car;
    }

    //标注在方法中，spring容器创建当前对象，完成赋值
    //方法使用的参数，自定义类型从ioc容器中获取
//    @Autowired
    public void setCar(Car car) {
        this.car = car;
    }

    public String toString(){
        return "Boss{car="+car+"}";
    }
}
