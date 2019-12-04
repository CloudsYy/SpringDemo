package com.cloud.testGeneric;

public class GenericFruit {
    static class Fruit{
        @Override
        public String toString() {
            return "Fruit";
        }
    }

    static class Apple extends Fruit{
        @Override
        public String toString() {
            return "Apple";
        }
    }

    static class Person{
        @Override
        public String toString() {
            return "Person";
        }
    }

    static class GenerateTest<T>{
       public void show_1(T t){
           System.out.println(t.toString());
           System.out.println(t.getClass());
       }

       //在泛型类中声明了一个泛型方法，使用泛型T，注意这个T是一种全新的类型，可以与泛型类中声明的T不是同一种类型。
       public <T> void show_2(T t){
           System.out.println(t.toString());
           System.out.println(t.getClass());
       }

       public <E> void show_3(E e){
           System.out.println(e.toString());
           System.out.println(e.getClass());
       }

    }

    public static void main(String[] args) {
        Person person = new Person();
        Apple apple = new Apple();


        GenerateTest<Fruit> generateTest = new GenerateTest<Fruit>();
        generateTest.show_2(apple);//apple为Fruit子类 此时传的是参数  不与number和integer一样
        generateTest.show_2(person);

        generateTest.show_1(apple);
//        generateTest.show_1(person);

        generateTest.show_3(apple);
        generateTest.show_3(person);

        //这里有点需要注意的是，泛型方法能独立于类和方法
//        静态方法无法访问类上定义的泛型；如果静态方法操作的引用数据类型不确定的时候，必须要将泛型定义在方法上。
//        即：如果静态方法要使用泛型的话，必须将静态方法也定义成泛型方法 。


    }
}
