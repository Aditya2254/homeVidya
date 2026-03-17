package com.homevidya.app.homevidya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class HomeVidyaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeVidyaApplication.class, args);
    }

}
