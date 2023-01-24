package com.example.integratedsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.integratedsystem.mapper")
public class IntegratedSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegratedSystemApplication.class, args);
    }

}
