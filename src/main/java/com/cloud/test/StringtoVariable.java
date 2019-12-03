package com.cloud.test;

public class StringtoVariable {

    //泛型可变参数测试
    public <T>void testString(T... strings) {
        System.out.println();
        for (T string : strings) {
            System.out.print(" " + string.getClass());
        }
    }

    public static void main(String[] args) {
        StringtoVariable stringtoVariable = new StringtoVariable();
        stringtoVariable.testString("123");
        String str[] = {"123","nihao","get","post"};
        stringtoVariable.testString(str);
        stringtoVariable.testString(new String());
        stringtoVariable.testString("*123*","","'123456'");
        stringtoVariable.testString(123);
        stringtoVariable.testString(123.45);
        stringtoVariable.testString(12L);
        stringtoVariable.testString();
    }
}
