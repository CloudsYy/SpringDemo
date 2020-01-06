package com.cloud.Service;

import com.cloud.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Qualifier("bookDao")
    @Autowired
    private BookDao bookDao;

    public void print(){
        System.out.println("bookDao = " + bookDao);
    }

    @Override
    public String toString() {
        return "BookService{bookDao="+bookDao+"}";
    }


}
