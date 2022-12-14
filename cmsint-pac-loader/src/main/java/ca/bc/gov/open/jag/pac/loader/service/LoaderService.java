package ca.bc.gov.open.jag.pac.loader.service;

import ca.bc.gov.open.jag.pac.loader.config.PacProperties;
import ca.bc.gov.open.pac.models.Client;
import ca.bc.gov.open.pac.models.eventStatus.PendingEventStatus;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
@Slf4j
public class LoaderService {

    private final WebServiceTemplate webServiceTemplate;
    private final PacProperties pacProperties;
    private final AmqpTemplate rabbitTemplate;

    public LoaderService(
            WebServiceTemplate webServiceTemplate,
            PacProperties pacProperties,
            AmqpTemplate rabbitTemplate) {
        this.webServiceTemplate = webServiceTemplate;
        this.pacProperties = pacProperties;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void processPAC(Client client) {
        var status = client.getStatus().getClass();
        var statesThatShouldNotBeProcessed = Arrays.asList(PendingEventStatus.class);
        if (statesThatShouldNotBeProcessed.contains(status)) sendToQueue(client);

        client.getStatus().getLoader(webServiceTemplate, pacProperties).process(client);
    }

    public void sendToQueue(Client client) {
        this.rabbitTemplate.convertAndSend(
                pacProperties.getExchangeName(), pacProperties.getPacRoutingKey(), client);
    }
}
