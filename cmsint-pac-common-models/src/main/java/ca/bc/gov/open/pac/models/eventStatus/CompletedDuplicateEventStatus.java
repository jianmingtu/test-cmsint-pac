package ca.bc.gov.open.pac.models.eventStatus;

import ca.bc.gov.open.pac.models.ords.OrdsProperties;
import org.springframework.web.client.RestTemplate;

public class CompletedDuplicateEventStatus extends EventStatus {

    public static final String METHOD_NAME = "updateToCompletedDuplicate";

    public CompletedDuplicateEventStatus(OrdsProperties ordProperties, RestTemplate restTemplate) {
        super(ordProperties, restTemplate);
    }

    @Override
    protected String getMethodName() {
        return METHOD_NAME;
    }
}
