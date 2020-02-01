package com;

import com.repos.UsrRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UsrRepository.class)
public class Shield {
    public static void main(String[] args) {
        SpringApplication.run(Shield.class, args);      // APP starter.
    }
}
