package com.cloud.test;

import java.util.ArrayList;
import java.util.List;

public class testT<T> {//泛型类，除此之外有泛型方法和泛型接口，具体定义可以看这个泛型类
    private T key;

    public testT(T key){
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public static void main(String[] args) {
        testT t = new testT("456789");
        testT t1 = new testT(123456);
        testT t2 = new testT(45.45);
        testT t3 = new testT(false);

        System.out.println(t.getKey());
        System.out.println(t1.getKey());
        System.out.println(t2.getKey());
        System.out.println(t3.getKey());
//        不能创建一个确切的泛型类型的数组↓
//        List<String>[] lists = new ArrayList<String>[10];
        List<?>[] ls = new ArrayList<?>[10];
        List<?>[] lss = new ArrayList<?>[10];
    }
}
