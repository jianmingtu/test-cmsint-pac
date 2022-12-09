package ca.bc.gov.open.jag.pac.poller.config;

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
    private String baseUrl = "http://test.com/";
    private String eventsEndpoint;
    private String processesEndpoint;
    private String eventsTypeEndpoint;
    private String successEndpoint;
    private String entriesEndpoint;
    private String demographicsEndpoint;
    private String cmsIntPath = "cmsint/";
    private String cmsPath = "cms/";
    private String modulePath = "module/";

    public String getCmsIntBaseUrl() {
        return baseUrl + cmsIntPath + modulePath;
    }

    public String getCmsBaseUrl() {
        return baseUrl + cmsPath + modulePath;
    }
}
