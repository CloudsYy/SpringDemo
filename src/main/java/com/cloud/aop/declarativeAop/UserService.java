package com.cloud.aop.declarativeAop;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void insert(){
        userDao.insert();
        System.out.println("新增一条数据...");
        int id1 = 12/0;
        // 上面即使报错了，也会直接插入到数据库中去的，这时候就需要用事务去控制这个，如果这个方法出现了错误，则整理的事务，则上面的插入数据库的数据也会回滚
    }
}
