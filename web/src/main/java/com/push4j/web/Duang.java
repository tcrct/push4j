package com.push4j.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.push4j","org.fastboot"})
public class Duang {

    public static void main(String[] args) {
        SpringApplication.run(Duang.class, args);
    }

}
