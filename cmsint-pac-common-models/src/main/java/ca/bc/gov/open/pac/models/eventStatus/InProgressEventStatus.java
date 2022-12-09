package ca.bc.gov.open.pac.models.eventStatus;

import ca.bc.gov.open.pac.models.Client;
import ca.bc.gov.open.pac.models.OrdsPropertiesInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class InProgressEventStatus extends EventStatus {

    public static final String METHOD_NAME = "updateToCompletedDuplicate";

    public InProgressEventStatus(OrdsPropertiesInterface ordProperties, RestTemplate restTemplate) {
        super(ordProperties, restTemplate);
    }

    @Override
    protected String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public Client updateToCompletedOk(Client client) {
        log.info("Updating Client status to 'Completed OK'");
        client.setStatus(new InProgressEventStatus(ordProperties, restTemplate));

        updateStatusOnServer(client, EventStatusCode.COMPLETED_OK);

        return client;
    }
}
