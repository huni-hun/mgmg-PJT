package com.ssafy.mgmgproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MgmgProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MgmgProjectApplication.class, args);
    }

}
