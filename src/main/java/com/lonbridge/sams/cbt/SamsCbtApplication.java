package com.lonbridge.sams.cbt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.lonbridge.sams"})
public class SamsCbtApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SamsCbtApplication.class, args);
    }
    
    @Override
    protected SpringApplicationBuilder configure ( SpringApplicationBuilder builder ) {
        return builder.sources ( SamsCbtApplication.class );
    }
}
