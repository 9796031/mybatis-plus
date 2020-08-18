package com.home.mybatis.plus;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liqingdong
 */
@SpringBootApplication
@MapperScans(@MapperScan("com.home.mybatis.plus.*.mapper"))
public class MybatisPlusBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusBootstrap.class, args);
    }
}
