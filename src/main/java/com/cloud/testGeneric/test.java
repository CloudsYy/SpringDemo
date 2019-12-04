package com.cloud.testGeneric;

import java.util.ArrayList;
import java.util.List;

public class test {
    public void showValue(Generic<Number> generic){
        System.out.println("generic = " + generic.getKey());
    }

    public void showKeyValue(Generic<? extends Number> generic){//利用通配符 "?" 给其限定边界
        System.out.println("generic = " + generic.getKey());
    }

    //        类型通配符一般是使用？代替具体的类型实参，注意了，此处’？’是类型实参，而不是类型形参 。
//        重要说三遍！此处’？’是类型实参，而不是类型形参 ！ 此处’？’是类型实参，而不是类型形参 ！
//        再直白点的意思就是，此处的？和Number、String、Integer一样都是一种实际的类型，可以把？看成所有类型的父类。
//        是一种真实的类型。
    public void showValue1(Generic<?> generic){
        System.out.println("generic = " + generic.getKey());
    }

    public static void main(String[] args) {
        Generic<Integer> generic = new Generic<Integer>(123);
        Generic<Number> generic1 = new Generic<Number>(123);
        Generic<String> generic2 = new Generic<String>("hello");

//        new test().showValue(generic); showKeyValue这个方法编译器会为我们报错：Generic<java.lang.Integer>
        new test().showValue(generic1);

        //使用通配符 '?'
        new test().showValue1(generic);
        new test().showValue(generic1);

        new test().showKeyValue(generic);
        new test().showKeyValue(generic1);
//        new test().showKeyValue(generic2);// 编译报错，因为String不为Number的子类


//        Generic<String> generic1 = new Generic<String>("123");
//
//        System.out.println("generic = " + generic.getKey());
//        System.out.println("generic1 = " + generic1.getKey());

//        泛型类，是在实例化类的时候指明泛型的具体类型；泛型方法，是在调用方法的时候指明泛型的具体类型
//        1）public 与 返回值中间<T>非常重要，可以理解为声明此方法为泛型方法。
//        2）只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法。
//        3）<T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T。
//        4）与泛型类的定义一样，此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型。

        //另外不能创建一个确切的泛型类型的数组 如下：↓  是不可以的
//        List<String> list = new ArrayList<String>[10];//编译报错 提示：generic array creation
        //如下：↓  是可以的
        List<?>[] lists = new ArrayList<?>[10];
        ArrayList[] list2 = new ArrayList[10];

        //sun 文章里写的一个例子
        /*List<String>[] lsa = new List<String>[10]; // Not really allowed.
        Object o = lsa;
        Object[] oa = (Object[]) o;
        List<Integer> li = new ArrayList<Integer>();
        li.add(new Integer(3));
        oa[1] = li; // Unsound, but passes run time store check
        String s = lsa[1].get(0); // Run-time error: ClassCastException.*/

        //修改后的
        //数组的类型不可以是类型变量，除非是采用通配符的方式，因为对于通配符的方式，最后取出数据是要做显式的类型转换的。
        List<?>[] lsa1 = new List<?>[10]; // OK, array of unbounded wildcard type.
        Object o1 = lsa1;
        Object[] oa1 = (Object[]) o1;
        List<Integer> li1 = new ArrayList<Integer>();
        li1.add(new Integer(3));
        oa1[1] = li1; // Correct.
        Integer i = (Integer) lsa1[1].get(0); // OK


    }
}
