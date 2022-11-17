package ca.bc.gov.open.jag.pac.poller.services;

import ca.bc.gov.open.jag.pac.poller.config.OrdsProperties;
import ca.bc.gov.open.pac.models.Client;
import ca.bc.gov.open.pac.models.ords.EventsEntity;
import ca.bc.gov.open.pac.models.ords.NewEventEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
@ExtendWith(OutputCaptureExtension.class)
@ActiveProfiles(value = "test")
public class PACPollerServiceTest {

    private static final String testString = "test";
    private static final String testUrl = "http://example.com";
    private static final String testEndpoint = "/endpoint";
    @Mock
    private RestTemplate mockRestTemplate;
    @Mock
    private RabbitTemplate mockRabbitTemplate;
    ;

    @Mock
    private OrdsProperties mockOrdsProperties;

    private static PACPollerService pacPollerService;

    @BeforeEach
    void beforeEachSetup() {
        MockitoAnnotations.openMocks(this);

        pacPollerService = new PACPollerService(mockOrdsProperties, null, mockRestTemplate, mockRabbitTemplate, null, null);
        when(mockOrdsProperties.getUsername()).thenReturn(testString);
        when(mockOrdsProperties.getPassword()).thenReturn(testString);
        when(mockOrdsProperties.getBaseUrl()).thenReturn(testUrl);
        when(mockOrdsProperties.getEventsEndpoint()).thenReturn(testEndpoint);
        when(mockOrdsProperties.getEventsTypeEndpoint()).thenReturn(testEndpoint);
        when(mockOrdsProperties.getSuccessEndpoint()).thenReturn(testEndpoint);
    }

    @Test
    void pollOrdsForNewRecordsBringsZeroNewRecords(CapturedOutput output) {
        ResponseEntity<NewEventEntity[]> responseEntity = new ResponseEntity<>(new NewEventEntity[0], HttpStatus.OK);

        when(mockRestTemplate.exchange(
                any(URI.class),
                any(HttpMethod.class),
                any(HttpEntity.class),
                eq(NewEventEntity[].class)
        ))
                .thenReturn(responseEntity);

        pacPollerService.pollOrdsForNewRecords();
        assertThat(output).contains("Pulled 0 new records");
    }

    @Test
    void pollOrdsForNewRecordsFailsDuringRequest(CapturedOutput output) {
        when(mockRestTemplate.exchange(
                any(URI.class),
                any(HttpMethod.class),
                any(HttpEntity.class),
                eq(EventsEntity.class)
        )).thenThrow(new RuntimeException("testing exception"));

        pacPollerService.pollOrdsForNewRecords();
        assertThat(output).contains("Failed to pull new records from the db");
    }

    @Test
    void pollOrdsForNewRecordsBringsNewRecords(CapturedOutput output) {
        NewEventEntity[] newEventsEntity = {
                new NewEventEntity("1", "1", "1"),
                new NewEventEntity("2", "2", "2")
        };
        ResponseEntity<NewEventEntity[]> responseEntity = new ResponseEntity<>(newEventsEntity, HttpStatus.OK);

        when(mockRestTemplate.exchange(
                any(URI.class),
                any(HttpMethod.class),
                any(HttpEntity.class),
                eq(NewEventEntity[].class)
        )).thenReturn(responseEntity);

        when(mockRabbitTemplate.convertSendAndReceive(anyString(), anyString(), any(Client.class))).thenReturn(null);

        pacPollerService.pollOrdsForNewRecords();
        assertThat(output).contains("Pulled " + newEventsEntity.length + " new records");
        verify(mockRabbitTemplate, times(1))
                .convertSendAndReceive(anyString(), anyString(), any(Client.class));
    }
}
