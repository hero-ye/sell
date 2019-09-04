package com.hero.sell;

import com.hero.sell.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SellApplication {

    public static void main(String[] args) {
        SpringApplication.run(SellApplication.class, args);
        System.out.println("已启动！");
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1, 1);
    }

}
