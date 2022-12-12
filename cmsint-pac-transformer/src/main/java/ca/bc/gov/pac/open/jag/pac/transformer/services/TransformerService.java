package ca.bc.gov.pac.open.jag.pac.transformer.services;

import ca.bc.gov.open.pac.models.Client;
import ca.bc.gov.open.pac.models.dateFormatters.DateFormatEnum;
import ca.bc.gov.open.pac.models.dateFormatters.DateFormatterInterface;
import ca.bc.gov.open.pac.models.eventStatus.PendingEventStatus;
import ca.bc.gov.pac.open.jag.pac.transformer.configurations.OrdsProperties;
import ca.bc.gov.pac.open.jag.pac.transformer.configurations.PacProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class TransformerService {

    private final RestTemplate restTemplate;
    private final OrdsProperties ordsProperties;
    private final PacProperties pacProperties;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public TransformerService(
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
    public void processPAC(final Client client) {

        if (!(client.getStatus() instanceof PendingEventStatus)) return;

        DateFormatterInterface dateFormatter =
                DateFormatEnum.valueOf(client.getComputerSystemCd().toUpperCase())
                        .getDateFormatter(pacProperties);

        var clientWithUpdatedDates =
                client.updateBirthDateFormat(dateFormatter)
                        .updateProbableDischargeDateDateFormat(dateFormatter)
                        .updateSysDateFormat(dateFormatter);

        clientWithUpdatedDates
                .getStatus()
                .setRestTemplate(restTemplate)
                .setOrdProperties(ordsProperties)
                .updateToInProgress(clientWithUpdatedDates);
        sendToQueue(clientWithUpdatedDates);
    }

    public void sendToQueue(Client client) {

        this.rabbitTemplate.convertAndSend(
                pacProperties.getExchangeName(), pacProperties.getPacRoutingKey(), client);
    }
}
