package com.wjy.wisdom;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan( value = "com.wjy.*.mapper")
@SpringBootApplication
public class wisdomApplication {

    public static void main(String[] args) {
        SpringApplication.run(wisdomApplication.class, args);
    }
}
