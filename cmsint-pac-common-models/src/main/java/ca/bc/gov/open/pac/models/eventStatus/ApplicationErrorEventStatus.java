package ca.bc.gov.open.pac.models.eventStatus;

import ca.bc.gov.open.pac.loader.ApplicationErrorEventLoader;
import ca.bc.gov.open.pac.loader.EventLoader;
import ca.bc.gov.open.pac.models.LoaderPacPropertiesInterface;
import ca.bc.gov.open.pac.models.OrdsPropertiesInterface;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.WebServiceTemplate;

public class ApplicationErrorEventStatus extends EventStatus {

    public ApplicationErrorEventStatus(
            OrdsPropertiesInterface ordProperties, RestTemplate restTemplate) {
        super(ordProperties, restTemplate);
    }

    @Override
    protected String getMethodName() {
        return null;
    }

    @Override
    public EventLoader getLoader(
            WebServiceTemplate webServiceTemplate, LoaderPacPropertiesInterface pacProperties) {
        return new ApplicationErrorEventLoader();
    }
}
