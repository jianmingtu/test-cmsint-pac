package ca.bc.gov.pac.open.jag.pac.transformer.services;

import ca.bc.gov.open.pac.models.Client;
import ca.bc.gov.open.pac.models.OrdsErrorLog;
import ca.bc.gov.open.pac.models.RequestSuccessLog;
import ca.bc.gov.open.pac.models.dateFormatters.DateFormatEnum;
import ca.bc.gov.open.pac.models.dateFormatters.DateFormatterInterface;
import ca.bc.gov.open.pac.models.eventStatus.PendingEventStatus;
import ca.bc.gov.open.pac.models.exceptions.ORDSException;
import ca.bc.gov.pac.open.jag.pac.transformer.configurations.OrdsProperties;
import ca.bc.gov.pac.open.jag.pac.transformer.configurations.PacProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class ConsumerService {

    private final RestTemplate restTemplate;
    private final OrdsProperties ordsProperties;
    private final PacProperties pacProperties;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public ConsumerService(
            RestTemplate restTemplate,
            OrdsProperties ordsProperties,
            PacProperties pacProperties,
            RabbitTemplate rabbitTemplate) {
        this.restTemplate = restTemplate;
        this.ordsProperties = ordsProperties;
        this.pacProperties = pacProperties;

        this.rabbitTemplate = rabbitTemplate;
    }

    // PACUpdate BPM
    public void processPAC(Client client) {

        if (!(client.getStatus() instanceof PendingEventStatus)) return;

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
}

//        send client back to the queue so the sender can pick it up.

//        The next lines should go to the sender
//        SynchronizeClient synchronizeClient = composeSoapServiceRequestBody(client);
//
//        invokeSoapService(synchronizeClient);
//
//        pacSuccess(client);
// End of BPM
