package com.cloud.test;


import com.cloud.MainConfigFile.MainConfigAutoWired;
import com.cloud.Service.BookService;
import com.cloud.dao.BookDao;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class testAutoWired {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigAutoWired.class);

    @Test
    public void print(){
        final String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("name = " + name);
        }
        BookService bookService = context.getBean(BookService.class);
        System.out.println("bookService = " + bookService);

//        BookDao bookDao = context.getBean(BookDao.class);当容器中我们BookDao存在多个bean，按类型获取，就会报错，No qualifying bean of type
//        System.out.println("bookDao = " + bookDao);主要原因可以想到，spring不知道我们要取得哪一个，不返回数组类型bean
        final Object bookDao2 = context.getBean("bookDao2");//那么我们可以id来获取，及这种形式获取
        System.out.println("bookDao2 = " + bookDao2);

//        final String[] names = context.getBeanDefinitionNames();
//        for (String name : names) {
//            System.out.println("name = " + name);
//        }
        context.close();
    }

}
