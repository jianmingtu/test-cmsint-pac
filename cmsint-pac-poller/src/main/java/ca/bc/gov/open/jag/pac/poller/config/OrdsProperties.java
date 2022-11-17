package ca.bc.gov.open.jag.pac.poller.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "ords")
@Data
public class OrdsProperties {
    private String username;
    private String password;
    private String baseUrl;
    private String eventsEndpoint;
    private String eventsTypeEndpoint;
    private String successEndpoint;
}
