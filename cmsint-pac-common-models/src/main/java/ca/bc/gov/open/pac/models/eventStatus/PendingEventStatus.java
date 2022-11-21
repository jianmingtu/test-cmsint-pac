package ca.bc.gov.open.pac.models.eventStatus;

import ca.bc.gov.open.pac.models.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class PendingEventStatus extends EventStatus {

    public PendingEventStatus(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public Client updateToCompletedDuplicate(Client client) {
        client.setEventStatus(new CompletedDuplicateEventStatus(restTemplate));

        updateStatusOnServer();

        return client;
    }

    @Override
    protected void updateStatusOnServer() {
        log.info("Updating Client status to 'Completed Duplicate'");

        //        restTemplate;

        throw new RuntimeException("aqui");
    }
}
