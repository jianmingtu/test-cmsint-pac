package ca.bc.gov.open.pac.models.eventStatus;

import ca.bc.gov.open.pac.models.Client;
import ca.bc.gov.open.pac.models.OrdsErrorLog;
import ca.bc.gov.open.pac.models.OrdsPropertiesInterface;
import ca.bc.gov.open.pac.models.RequestSuccessLog;
import ca.bc.gov.open.pac.models.exceptions.ORDSException;
import ca.bc.gov.open.pac.models.ords.UpdateEntryEntity;
import java.io.Serializable;
import java.net.URI;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@EqualsAndHashCode
@NoArgsConstructor
@Slf4j
public abstract class EventStatus implements Serializable {

    protected transient RestTemplate restTemplate;
    protected transient OrdsPropertiesInterface ordProperties;

    public EventStatus(OrdsPropertiesInterface ordProperties, RestTemplate restTemplate) {
        this.ordProperties = ordProperties;
        this.restTemplate = restTemplate;
    }

    public Client updateToPending(Client client) {
        throw new UnsupportedOperationException(
                "The status of the event cannot be updated to Pending");
    }

    public Client updateToCompletedDuplicate(Client client) {
        throw new UnsupportedOperationException(
                "The status of the event cannot be updated to Completed Duplicate");
    }

    public Client updateToInProgress(Client client) {
        throw new UnsupportedOperationException(
                "The status of the event cannot be updated to In Progress");
    }

    public Client updateToCompletedOk(Client client) {
        throw new UnsupportedOperationException(
                "The status of the event cannot be updated to Completed OK");
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
