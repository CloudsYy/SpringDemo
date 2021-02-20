package com.cloud.aop.declarativeAop;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insert(){
        String sql = "INSERT INTO User(id,name,hobby) VALUES (?,?,?)";
        jdbcTemplate.update(sql,30,"xiaoming","playtennis");

    }
}
