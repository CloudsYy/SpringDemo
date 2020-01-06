package com.cloud.dao;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Repository;

@Repository
@Data
@ToString
public class BookDao {
    private String label = "1";
}
