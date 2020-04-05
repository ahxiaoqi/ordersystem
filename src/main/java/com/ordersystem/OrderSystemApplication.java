package com.ordersystem;

import com.ordersystem.util.SnowFlake;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@MapperScan("com.orderSystem.mapper")
@SpringBootApplication
public class OrderSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderSystemApplication.class, args);
    }

    @Bean
    public SnowFlake SnowFlake() {
        return new SnowFlake(1, 1);
    }

}
