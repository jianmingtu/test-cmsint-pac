package ca.bc.gov.pac.open.jag.pac.consumer.services;

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
public class ConsumerService {
    private final ObjectMapper objectMapper;

    private final PACService pacService;

    @Autowired
    public ConsumerService(ObjectMapper objectMapper, PACService pacService) {
        this.objectMapper = objectMapper;
        this.pacService = pacService;
    }

    // Disable PAC Queue until PAC is ready to go
    @RabbitListener(queues = "${pac.pac-queue}")
    public void receivePACMessage(@Payload Message<Client> message) throws IOException {
        Client client = message.getPayload();
        try {
            pacService.processPAC(client);
        } catch (Exception ignored) {
            log.error("PAC BPM ERROR: " + message + " not processed successfully");
        }
        log.info(new ObjectMapper().writeValueAsString(client));
    }
}
