package com.imarchenko.ua;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.imarchenko.ua")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
