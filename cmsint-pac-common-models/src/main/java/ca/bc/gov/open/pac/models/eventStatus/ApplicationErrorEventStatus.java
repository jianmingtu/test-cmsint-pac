package ca.bc.gov.open.pac.models.eventStatus;

import ca.bc.gov.open.pac.models.OrdsPropertiesInterface;
import org.springframework.web.client.RestTemplate;

public class ApplicationErrorEventStatus extends EventStatus {

    public ApplicationErrorEventStatus(
            OrdsPropertiesInterface ordProperties, RestTemplate restTemplate) {
        super(ordProperties, restTemplate);
    }

    @Override
    protected String getMethodName() {
        return null;
    }
}
