package ca.bc.gov.open.jag.pac.extractor;

import ca.bc.gov.open.jag.pac.extractor.config.OrdsProperties;
import ca.bc.gov.open.jag.pac.extractor.config.QueueConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.Mock;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.WebServiceTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PacExtractorApplicationTests {

    @Qualifier("pac-queue")
    private org.springframework.amqp.core.Queue pacQueue;

    @MockBean private RabbitTemplate rabbitTemplate;
    @MockBean private AmqpAdmin amqpAdmin;

    private QueueConfig queueConfig;

    @Autowired private ObjectMapper objectMapper;

    @Mock private WebServiceTemplate soapTemplate = new WebServiceTemplate();

    @Mock private RestTemplate restTemplate = new RestTemplate();
    @Mock private OrdsProperties mockOrdsProperties;

    //    @Test
    //    void testPollOrdsForNewRecords() throws JsonProcessingException {
    //
    //        List<PACModel> out = new ArrayList<>();
    //        var PACModel = new PACModel();
    //        out.add(PACModel);
    //        ResponseEntity<List<PACModel>> responseEntity = new ResponseEntity<>(out,
    // HttpStatus.OK);
    //
    //        // Set up to mock ords response
    //        when(restTemplate.exchange(
    //                        Mockito.any(URI.class),
    //                        Mockito.eq(HttpMethod.GET),
    //                        Mockito.<HttpEntity<String>>any(),
    //                        Mockito.<ParameterizedTypeReference<List<PACModel>>>any()))
    //                .thenReturn(responseEntity);
    //
    //        PACPollerService pacPollerService =
    //                new PACPollerService(
    //                        mockOrdsProperties, pacQueue, restTemplate, rabbitTemplate, amqpAdmin,
    // queueConfig);
    //    }
}
