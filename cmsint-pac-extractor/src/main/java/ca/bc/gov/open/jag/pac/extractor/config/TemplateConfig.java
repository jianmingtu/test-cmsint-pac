package ca.bc.gov.open.jag.pac.extractor.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
public class TemplateConfig {
    @Value("${ords.cmsIntUsername}")
    private String cmsIntUsername;

    @Value("${ords.cmsIntPassword}")
    private String cmsIntPassword;

    @Value("${ords.cmsUsername}")
    private String cmsUsername;

    @Value("${ords.cmsPassword}")
    private String cmsPassword;

    @Bean(name = "restTemplateCMSInt")
    public RestTemplate restTemplateCMSInt() {

        log.info("cmsIntUsername:" + cmsIntUsername + " , cmsIntPassword:" + cmsIntPassword);

        log.info("cmsUsername:" + cmsUsername + " , cmsPassword:" + cmsPassword);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate
                .getInterceptors()
                .add(
                        (request, body, execution) -> {
                            log.info("URL:" + request.getURI().toString());
                            log.info(
                                    "cmsIntUsername:"
                                            + cmsIntUsername
                                            + " , cmsIntPassword:"
                                            + cmsIntPassword);
                            String auth = cmsIntUsername + ":" + cmsIntPassword;
                            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
                            request.getHeaders()
                                    .add("Authorization", "Basic " + new String(encodedAuth));
                            return execution.execute(request, body);
                        });
        return restTemplate;
    }

    @Bean(name = "restTemplateCMS")
    public RestTemplate restTemplateCMS() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate
                .getInterceptors()
                .add(
                        (request, body, execution) -> {
                            log.info("URL:" + request.getURI().toString());
                            log.info(
                                    "cmsUsername:" + cmsUsername + " , cmsPassword:" + cmsPassword);
                            String auth = cmsUsername + ":" + cmsPassword;
                            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
                            request.getHeaders()
                                    .add("Authorization", "Basic " + new String(encodedAuth));
                            return execution.execute(request, body);
                        });
        return restTemplate;
    }
}
