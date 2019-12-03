package com.cloud.test;


import java.util.ArrayList;

public class TestFor {
    public static long startTime;
    public static long endTime;
    public static int a = 1;
    public static int b = 1;
    public static int c = 1;
    public static int d;
    public static void main(String[] args) {
//        嵌套循环 --- 性能对比  外小内大  外大内小
        startTime = System.nanoTime();
        for (int i = 0; i < 200000; i++) {
            for (int j = 0; j < 100000; j++) {
                for (int k = 0; k < 10; k++) {

                }
            }
        }
        endTime = System.nanoTime();
        System.out.println("外大内小耗时："+ (endTime - startTime));

        startTime = System.nanoTime();
        for (int u = 1; u <10 ; u++) {
            for (int y = 1; y < 100000; y++) {
                for (int i = 0; i < 200000; i++) {
                }
            }
        }
        endTime = System.nanoTime();
        System.out.println("外小内大耗时："+(endTime - startTime));

        System.out.println("-----------隔离----------");

//        2、提取与循环无关的表达式
        startTime = System.nanoTime();
        for (int i = 0; i < 200000; i++) {
            i=i*a*b;
        }
        endTime = System.nanoTime();
        System.out.println("提取前耗时："+(endTime-startTime));

        startTime = System.nanoTime();
        c = a*b;
        for (int i = 0; i < 200000; i++) {
            i=c*i;
        }
        endTime = System.nanoTime();
        System.out.println("提取后耗时：" + (endTime-startTime));

        System.out.println("-----------隔离----------");

//        3.消除循环终止判断时的方法调用
        ArrayList<String> list = new ArrayList<String>();
        list.add("爱护世界");
        list.add("保护环境");
        list.add("爱护地球");
        list.add("从我做起");
        startTime = System.nanoTime();
        for (int i = 0; i < list.size(); i++) {
        }
        endTime = System.nanoTime();
        System.out.println("消除前：" + (endTime - startTime));

        startTime = System.nanoTime();
        int size = list.size();
        for (int i = 0; i < size; i++) {
        }
        endTime = System.nanoTime();
        System.out.println("消除后：" + (endTime - startTime));

        System.out.println("-----------隔离----------");

//        4、异常捕获
        startTime = System.nanoTime();
        for (int i = 0; i < 10000000; i++) {
            try {
            } catch (Exception e) {
            }
        }
        endTime = System.nanoTime();
        System.out.println("在内部捕获异常耗时："+(endTime - startTime));

        startTime = System.nanoTime();
        try {
            for (int i = 0; i < 10000000; i++) {
            }
        } catch (Exception e) {
        }
        endTime = System.nanoTime();
        System.out.println("在外部捕获异常耗时：" + (endTime - startTime));

    }
}
