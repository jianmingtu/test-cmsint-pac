package ca.bc.gov.pac.open.jag.pac.transformer.services;

import ca.bc.gov.open.pac.models.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QueueListenerService {
    private final ObjectMapper objectMapper;

    private final ConsumerService consumerService;

    @Autowired
    public QueueListenerService(ObjectMapper objectMapper, ConsumerService consumerService) {
        this.objectMapper = objectMapper;
        this.consumerService = consumerService;
    }

    @RabbitListener(queues = "${pac.pac-queue}")
    public void receivePACMessage(@Payload Message<Client> message) throws IOException {
        Client client = message.getPayload();
        try {
            consumerService.processPAC(client);
        } catch (Exception ignored) {
            log.error("PAC BPM ERROR: " + message + " not processed successfully");
        }
        log.info(new ObjectMapper().writeValueAsString(client));
    }
}
