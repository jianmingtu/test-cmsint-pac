package ca.bc.gov.open.pac.models.eventStatus;

import ca.bc.gov.open.pac.models.Client;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@EqualsAndHashCode
@Component
@NoArgsConstructor
@Slf4j
public abstract class EventStatus {

    protected RestTemplate restTemplate;

    public EventStatus(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Client updateToPending(Client client) {
        throw new RuntimeException("The status of the event cannot be updated to Pending");
    }

    public Client updateToCompletedDuplicate(Client client) {
        throw new RuntimeException(
                "The status of the event cannot be updated to Completed Duplicate");
    }

    protected abstract void updateStatusOnServer();
}
