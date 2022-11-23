package ca.bc.gov.open.pac.models.eventStatus;

import ca.bc.gov.open.pac.models.Client;
import ca.bc.gov.open.pac.models.OrdsErrorLog;
import ca.bc.gov.open.pac.models.RequestSuccessLog;
import ca.bc.gov.open.pac.models.exceptions.ORDSException;
import ca.bc.gov.open.pac.models.ords.OrdsProperties;
import ca.bc.gov.open.pac.models.ords.UpdateEntryEntity;
import java.net.URI;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@EqualsAndHashCode
@NoArgsConstructor
@Slf4j
public abstract class EventStatus {

    protected RestTemplate restTemplate;
    protected OrdsProperties ordProperties;

    public EventStatus(OrdsProperties ordProperties, RestTemplate restTemplate) {
        this.ordProperties = ordProperties;
        this.restTemplate = restTemplate;
    }

    public Client updateToPending(Client client) {
        throw new UnsupportedOperationException("The status of the event cannot be updated to Pending");
    }

    public Client updateToCompletedDuplicate(Client client) {
        throw new UnsupportedOperationException(
                "The status of the event cannot be updated to Completed Duplicate");
    }

    protected void updateStatusOnServer(Client client, EventStatusCode eventStatusCode) {
        URI url =
                UriComponentsBuilder.fromHttpUrl(
                                ordProperties.getCmsBaseUrl() + ordProperties.getEntriesEndpoint())
                        .build()
                        .toUri();

        try {
            restTemplate.put(url, new UpdateEntryEntity(client, eventStatusCode));
            log.info(new RequestSuccessLog("Request Success", url.getPath()).toString());
        } catch (Exception ex) {
            log.error(
                    new OrdsErrorLog(
                                    "Error received from ORDS",
                                    getMethodName(),
                                    ex.getMessage(),
                                    client)
                            .toString());
            throw new ORDSException();
        }
    }

    protected abstract String getMethodName();
}
