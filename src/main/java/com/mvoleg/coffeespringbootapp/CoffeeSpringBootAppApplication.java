package com.mvoleg.coffeespringbootapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class CoffeeSpringBootAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeSpringBootAppApplication.class, args);
    }
}
