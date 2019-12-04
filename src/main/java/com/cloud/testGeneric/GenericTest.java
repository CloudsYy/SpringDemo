package com.cloud.testGeneric;

public class GenericTest {
    class Generic<T> {//这里公共的泛型类和公共类是不会冲突的

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
//    public E setKey(E key){   这里显然有问题，编译会报错，问题为类的声明中并未声明E，而返回值为E，故无法识别。
//        this.key = key;
//    }
    //    public <T> T setKey(E key){   这里显然有问题，编译会报错，问题为类的声明中并未声明E，而返回值为E，故无法识别。
//        this.key = key;
//    }

     /**
         * 这才是一个真正的泛型方法。
         * 首先在public与返回值之间的<T>必不可少，这表明这是一个泛型方法，并且声明了一个泛型T
         * 这个T可以出现在这个泛型方法的任意位置.
         * 泛型的数量也可以为任意多个
         *    如：public <T,K> K showKeyName(Generic<T> container){
         *        ...
         *        }
         */
     public <T> T showValue(){  //这为真正的泛型类
         return null;
     }
    //这也不是一个泛型方法，这就是一个普通的方法，只是使用了Generic<Number>这个泛型类做形参而已。
    public void showKeyValue1(Generic<Number> obj){
        System.out.println("泛型测试,"+"key value is " + obj.getKey());
    }

    //这也不是一个泛型方法，这也是一个普通的方法，只不过使用了泛型通配符?
    //同时这也印证了泛型通配符章节所描述的，?是一种类型实参，可以看做为Number等所有类的父类
    public void showKeyValue2(Generic<?> obj){
        System.out.println("泛型测试," + "key value is " + obj.getKey());
    }

//    public void shwoValue(T e){ //编译时出错：cannot resolve symbol T
//    }
}
