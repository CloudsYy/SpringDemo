package com.cloud.testGeneric;

public class Generic<T> {//这里公共的泛型类和公共类是不会冲突的

    //key这个成员变量的类型为T 由外部指定T的类型，不能为E其他泛型标识
    //泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
    private T key;

    public Generic(T i) {
        this.key = i;
    }

    public void setKey(T key) {
        this.key = key;
    }//这个不是泛型方法 只是参数类型为T

    public T getKey() {
        return key;
    }//这个不是泛型方法 只是返回值为泛型T
}
