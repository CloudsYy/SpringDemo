package com.cloud.testGeneric;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.util.ArrayList;
import java.util.List;

public class test1 {
    public static void main(String[] args) {
//        List list = new ArrayList();
//        list.add(23);
//        list.add("hello");
//        for (int i = 0; i < list.size(); i++) {
//            String item = (String) list.get(i);
//            System.out.println("item = " + item);//java.lang.ClassCastException
//        }

        ArrayList<String> list1 = new ArrayList<String>();
        ArrayList<Integer> list2 = new ArrayList<Integer>();

        Class aClass = list1.getClass();
        Class bClass = list2.getClass();

        System.out.println("aClass = " + aClass);
        System.out.println("bClass = " + bClass);

        if (aClass.equals(bClass)) {
            System.out.println("泛型类型相同");
        }
        //从以上可以看出，编译后泛型就去掉了，也就是泛型擦除，意为：在jvm中，运行的是字节码，泛型是编译层面的，
        //也就是说，泛型被编译成字节码后到了jvm，就是普通类型了。即泛型不在运行时阶段



    }
}
