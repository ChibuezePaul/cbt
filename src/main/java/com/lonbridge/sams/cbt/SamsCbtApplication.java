package com.lonbridge.sams.cbt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.lonbridge.sams"})
public class SamsCbtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SamsCbtApplication.class, args);
    }

}
