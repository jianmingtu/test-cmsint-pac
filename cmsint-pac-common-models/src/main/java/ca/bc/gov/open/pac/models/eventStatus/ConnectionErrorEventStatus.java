package ca.bc.gov.open.pac.models.eventStatus;

import ca.bc.gov.open.pac.models.OrdsPropertiesInterface;
import org.springframework.web.client.RestTemplate;

public class ConnectionErrorEventStatus extends EventStatus {

    public ConnectionErrorEventStatus(
            OrdsPropertiesInterface ordProperties, RestTemplate restTemplate) {
        super(ordProperties, restTemplate);
    }

    @Override
    protected String getMethodName() {
        return null;
    }
}
