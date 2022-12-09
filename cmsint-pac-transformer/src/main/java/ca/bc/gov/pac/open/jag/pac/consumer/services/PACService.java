package ca.bc.gov.pac.open.jag.pac.consumer.services;

import ca.bc.gov.open.pac.models.Client;
import ca.bc.gov.open.pac.models.OrdsErrorLog;
import ca.bc.gov.open.pac.models.RequestSuccessLog;
import ca.bc.gov.open.pac.models.dateFormatters.DateFormatEnum;
import ca.bc.gov.open.pac.models.dateFormatters.DateFormatterInterface;
import ca.bc.gov.open.pac.models.eventStatus.PendingEventStatus;
import ca.bc.gov.open.pac.models.eventTypeCode.EventTypeEnum;
import ca.bc.gov.open.pac.models.eventTypeCode.SynchronizeClient;
import ca.bc.gov.open.pac.models.exceptions.ORDSException;
import ca.bc.gov.pac.open.jag.pac.consumer.configurations.OrdsProperties;
import ca.bc.gov.pac.open.jag.pac.consumer.configurations.PacProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileOutputStream;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
@Slf4j
public class PACService {
    private final WebServiceTemplate webServiceTemplate;
    private final RestTemplate restTemplate;
    private final OrdsProperties ordsProperties;
    private final PacProperties pacProperties;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public PACService(
            RestTemplate restTemplate,
            OrdsProperties ordsProperties,
            PacProperties pacProperties,
            ObjectMapper objectMapper,
            WebServiceTemplate webServiceTemplate,
            RabbitTemplate rabbitTemplate) {
        this.restTemplate = restTemplate;
        this.ordsProperties = ordsProperties;
        this.pacProperties = pacProperties;
        this.webServiceTemplate = webServiceTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    // PACUpdate BPM
    public void processPAC(Client client) throws IOException {

        if (!(client.getStatus() instanceof PendingEventStatus)) return;
        new FileOutputStream("client.object").write(SerializationUtils.serialize(client));

        DateFormatterInterface dateFormatter =
                DateFormatEnum.valueOf(client.getComputerSystemCd().toUpperCase())
                        .getDateFormatter(pacProperties);

        var clientWithUpdatedDates =
                client.updateBirthDateFormat(dateFormatter)
                        .updateProbableDischargeDateDateFormat(dateFormatter)
                        .updateSysDateFormat(dateFormatter);

        client.getStatus().updateToInProgress(clientWithUpdatedDates);
        sendToQueue(clientWithUpdatedDates);
    }

    public void sendToQueue(Client client) {
        this.rabbitTemplate.convertAndSend(
                pacProperties.getExchangeName(), pacProperties.getPacRoutingKey(), client);
    }

    private void pacSuccess(Client client, Client eventClient) throws JsonProcessingException {
        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(
                                ordsProperties.getCmsPath() + ordsProperties.getSuccessEndpoint())
                        .queryParam("clientNumber", client.getClientNumber())
                        .queryParam("eventSeqNum", client.getEventSeqNum())
                        .queryParam("computerSystemCd", client.getComputerSystemCd());
        try {
            restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.POST,
                    new HttpEntity<>(client, new HttpHeaders()),
                    new ParameterizedTypeReference<>() {});
            log.info(new RequestSuccessLog("Request Success", "updateSuccess").toString());
            if (eventClient.getStatus().equals("0")) log.info("PAC update success");
        } catch (Exception ex) {
            log.error(
                    new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "updateSuccess",
                                    ex.getMessage(),
                                    client)
                            .toString());
            throw new ORDSException();
        }
    }

    private void invokeSoapService(SynchronizeClient synchronizeClient)
            throws JsonProcessingException {
        // Invoke Soap Service
        try {
            webServiceTemplate.marshalSendAndReceive(
                    pacProperties.getServiceUrl(), synchronizeClient);
            log.info(new RequestSuccessLog("Request Success", "synchronizeClient").toString());
        } catch (Exception ex) {
            log.error(
                    new OrdsErrorLog(
                                    "Error received from SOAP SERVICE - synchronizeClient",
                                    "pacUpdate",
                                    ex.getMessage(),
                                    synchronizeClient)
                            .toString());
        }
    }

    private SynchronizeClient composeSoapServiceRequestBody(Client client) {
        // Compose Soap Service Request Body
        SynchronizeClient synchronizeClient = null;
        try {
            synchronizeClient =
                    EventTypeEnum.valueOf(client.getEventTypeCode()).getSynchronizeClient(client);
        } catch (IllegalArgumentException e) {
            log.warn("Received EventTypeCode " + client.getEventTypeCode() + " is not expected");
        }
        return synchronizeClient;
    }

    private HttpEntity<Client> sendUpdateRequest(Client client) {
        UriComponentsBuilder builder;
        builder =
                UriComponentsBuilder.fromHttpUrl(
                        ordsProperties.getCmsPath() + ordsProperties.getSuccessEndpoint());
        HttpEntity<Client> respClient =
                restTemplate.exchange(
                        builder.toUriString(),
                        HttpMethod.POST,
                        new HttpEntity<>(client, new HttpHeaders()),
                        new ParameterizedTypeReference<>() {});

        log.info(new RequestSuccessLog("Request Success", "pacUpdate").toString());

        return respClient;
    }

    private HttpEntity<Client> pacSuccess(Client client) {
        try {
            HttpEntity<Client> respClient = sendUpdateRequest(client);
            if (respClient.getBody().getStatus().equals("0")) {
                log.info("PAC update cancel");
                return null;
            }
            return respClient;
        } catch (Exception ex) {
            log.error(
                    new OrdsErrorLog(
                                    "Error received from ORDS",
                                    "pacUpdate",
                                    ex.getMessage(),
                                    client)
                            .toString());
            throw new ORDSException();
        }
    }
}

//        send client back to the queue so the sender can pick it up.
// extractor -> transformer -> loader

//        The next lines should go to the sender
//        SynchronizeClient synchronizeClient = composeSoapServiceRequestBody(client);
//
//        invokeSoapService(synchronizeClient);
//
//        pacSuccess(client);
// End of BPM
