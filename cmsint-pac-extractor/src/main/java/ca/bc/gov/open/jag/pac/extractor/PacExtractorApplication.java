package ca.bc.gov.open.jag.pac.extractor;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableRabbit
@EnableScheduling
public class PacExtractorApplication {

    public static void main(String[] args) {
        SpringApplication.run(PacExtractorApplication.class, args);
    }
}
