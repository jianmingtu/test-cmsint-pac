package ca.bc.gov.open.pac.models.eventStatus;

import ca.bc.gov.open.pac.loader.CompletedDuplicateEventLoader;
import ca.bc.gov.open.pac.loader.EventLoader;
import ca.bc.gov.open.pac.models.LoaderPacPropertiesInterface;
import ca.bc.gov.open.pac.models.OrdsPropertiesInterface;
import java.io.Serializable;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.WebServiceTemplate;

public class CompletedDuplicateEventStatus extends EventStatus implements Serializable {

    public static final String METHOD_NAME = "updateToCompletedDuplicate";

    public CompletedDuplicateEventStatus(
            OrdsPropertiesInterface ordProperties, RestTemplate restTemplate) {
        super(ordProperties, restTemplate);
    }

    @Override
    protected String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public EventLoader getLoader(
            WebServiceTemplate webServiceTemplate, LoaderPacPropertiesInterface pacProperties) {
        return new CompletedDuplicateEventLoader();
    }
}
