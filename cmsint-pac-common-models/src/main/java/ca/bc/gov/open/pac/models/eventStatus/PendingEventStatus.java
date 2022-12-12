package ca.bc.gov.open.pac.models.eventStatus;

import ca.bc.gov.open.pac.models.Client;
import ca.bc.gov.open.pac.models.OrdsPropertiesInterface;
import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class PendingEventStatus extends EventStatus implements Serializable {

    public static final String METHOD_NAME = "updateToCompletedDuplicate";

    public PendingEventStatus(OrdsPropertiesInterface ordProperties, RestTemplate restTemplate) {
        super(ordProperties, restTemplate);
    }

    @Override
    public Client updateToCompletedDuplicate(Client client) {
        log.info("Updating Client status to 'Completed Duplicate'");
        client.setStatus(new CompletedDuplicateEventStatus(ordProperties, restTemplate));

        updateStatusOnServer(client, EventStatusCode.COMPLETED_DUPLICATE);

        return client;
    }

    @Override
    public Client updateToInProgress(Client client) {
        log.info("Updating Client status to 'In Progress'");
        client.setStatus(new InProgressEventStatus(ordProperties, restTemplate));

        updateStatusOnServer(client, EventStatusCode.IN_PROGRESS);

        return client;
    }

    @Override
    protected String getMethodName() {
        return METHOD_NAME;
    }
}
