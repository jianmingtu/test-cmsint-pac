package ca.bc.gov.pac.open.jag.pac.transformer.configurations;

import ca.bc.gov.open.pac.models.OrdsPropertiesInterface;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ords")
@Data
public class OrdsProperties implements OrdsPropertiesInterface {
    private String username;
    private String password;
    private String baseUrl;
    private String entriesEndpoint;
    private String cmsIntPath;
    private String cmsPath;
    private String modulePath;

    public String getCmsIntBaseUrl() {
        return baseUrl + cmsIntPath + modulePath;
    }

    public String getCmsBaseUrl() {
        return baseUrl + cmsPath + modulePath;
    }
}
