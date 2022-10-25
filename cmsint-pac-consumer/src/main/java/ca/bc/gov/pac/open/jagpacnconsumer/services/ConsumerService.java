package ca.bc.gov.pac.open.jagpacnconsumer.services;

import ca.bc.gov.open.pac.models.PACModel;
import ca.bc.gov.open.pac.models.PingModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Autowired
    public ConsumerService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    // Disable PAC Queue until PAC is ready to go
    @RabbitListener(queues = "${pac.pac-queue}")
    public void receivePACMessage(@Payload Message<PACModel> message)
            throws JsonProcessingException {
        System.out.println(new ObjectMapper().writeValueAsString(message.getPayload()));
    }

    @RabbitListener(queues = "${pac.ping-queue}")
    public void receivePingMessage(@Payload Message<PingModel> message)
            throws JsonProcessingException {
        System.out.println(new ObjectMapper().writeValueAsString(message.getPayload()));
    }
}
