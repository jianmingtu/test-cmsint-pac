package ca.bc.gov.open.jag.pac.loader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableRabbit
public class PacLoaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PacLoaderApplication.class, args);
    }
}
