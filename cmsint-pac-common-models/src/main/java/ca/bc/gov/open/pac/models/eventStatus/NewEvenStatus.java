package ca.bc.gov.open.pac.models.eventStatus;

import ca.bc.gov.open.pac.models.Client;
import ca.bc.gov.open.pac.models.ords.OrdsProperties;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@NoArgsConstructor
@Slf4j
public class NewEvenStatus extends EventStatus {

    public NewEvenStatus(OrdsProperties ordProperties, RestTemplate restTemplate) {
        super(ordProperties, restTemplate);
    }

    @Override
    public Client updateToPending(Client client) {
        log.info("Updating Client status to 'Pending'");

        client.setStatus(new PendingEventStatus(ordProperties, restTemplate));

        updateStatusOnServer(client, EventStatusCode.PENDING);

        return client;
    }

    @Override
    protected String getMethodName() {
        return "updateToPending";
    }
}
