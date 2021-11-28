package com.hanghae.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HanghaeDeliveryApplication {

    public static void main ( String[] args ) {
        SpringApplication.run(HanghaeDeliveryApplication.class, args);
    }

}
