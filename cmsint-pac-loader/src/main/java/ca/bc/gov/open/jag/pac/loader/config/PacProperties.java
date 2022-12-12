package ca.bc.gov.open.jag.pac.loader.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "pac")
@Data
public class PacProperties {

    private String serviceUrl;
    private String pacQueue;
    private String pacRoutingKey;
    private String exchangeName;
}
