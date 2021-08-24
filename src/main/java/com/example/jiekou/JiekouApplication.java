package com.example.jiekou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.jiekou.mapper")
public class JiekouApplication {


    public static void main(String[] args) {
        SpringApplication.run(JiekouApplication.class, args);
        System.out.println("=====spring boot start success====");
    }

}
