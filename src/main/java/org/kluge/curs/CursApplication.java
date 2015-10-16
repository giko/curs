package org.kluge.curs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
class CursApplication {

    public static void main(String[] args) {
        SpringApplication.run(CursApplication.class, args);
    }
}
