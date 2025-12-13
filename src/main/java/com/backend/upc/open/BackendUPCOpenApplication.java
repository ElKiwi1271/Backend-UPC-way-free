package com.backend.upc.open;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BackendUPCOpenApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendUPCOpenApplication.class, args);
    }

}
