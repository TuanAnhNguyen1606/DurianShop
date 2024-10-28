package com.example.exe201;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;


@SpringBootApplication
@RequiredArgsConstructor
@Order(1)
public class Exe201Application {

    private final Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(Exe201Application.class, args);
    }

    @PostConstruct()
    public void log(){
        System.out.println(environment.getProperty("spring.datasource.url"));
    }
}
