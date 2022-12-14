package ca.bc.gov.open.jag.pac.loader.service;

import ca.bc.gov.open.pac.models.Client;
import ca.bc.gov.open.pac.models.exceptions.ORDSException;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QueueListenerService {

    private final LoaderService loaderService;

    public QueueListenerService(LoaderService loaderService) {
        this.loaderService = loaderService;
    }

    @RabbitListener(queues = "${pac.pac-queue}")
    public void receivePACMessage(@Payload Message<Client> message) throws IOException {
        Client client = message.getPayload();
        try {
            loaderService.processPAC(client);
        } catch (ORDSException ex) {
            log.error("PAC BPM ERROR: " + message + " not processed successfully");
            client.getStatus().updateToConnectionError(client);
        } catch (Exception ignored) {
            log.error("PAC BPM ERROR: " + message + " not processed successfully");
            client.getStatus().updateToApplicationError(client);
        }
    }
}
