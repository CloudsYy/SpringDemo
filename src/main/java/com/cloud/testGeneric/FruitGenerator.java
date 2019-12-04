package com.cloud.testGeneric;

import java.util.Random;

public class FruitGenerator implements Genertor<String> { //这里为泛型实参，指定泛型实参的类型

    private String[] fruit = new String[]{"apple","banana","pear"};

    public String next() {
        Random random = new Random();
        return fruit[random.nextInt(3)];
    }
}
