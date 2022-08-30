package dev.omedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"dev.omedia.domains"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
