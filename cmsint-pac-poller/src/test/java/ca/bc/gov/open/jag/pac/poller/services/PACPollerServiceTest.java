package ca.bc.gov.open.jag.pac.poller.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import ca.bc.gov.open.pac.models.Client;
import ca.bc.gov.open.pac.models.DemographicInfo;
import ca.bc.gov.open.pac.models.eventStatus.PendingEventStatus;
import ca.bc.gov.open.pac.models.exceptions.ORDSException;
import ca.bc.gov.open.pac.models.ords.DemographicsEntity;
import ca.bc.gov.open.pac.models.ords.EventEntity;
import ca.bc.gov.open.pac.models.ords.EventsEntity;
import ca.bc.gov.open.pac.models.ords.NewerEventEntity;
import ca.bc.gov.open.pac.models.ords.OrdsProperties;
import ca.bc.gov.open.pac.models.ords.ProcessEntity;
import ca.bc.gov.open.pac.models.ords.UpdateEntryEntity;
import java.net.URI;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

// @ExtendWith(MockitoExtension.class)
@ExtendWith(OutputCaptureExtension.class)
@ActiveProfiles(value = "test")
public class PACPollerServiceTest {

    private static final String testString = "test";
    private static final String testUrl = "http://example.com";
    private static final String testEndpoint = "/endpoint";
    @Mock private RestTemplate mockRestTemplate;
    @Mock private RabbitTemplate mockRabbitTemplate;
    @Mock private AmqpAdmin mockedAmqpAdmin;

    @Mock private OrdsProperties mockOrdsProperties;

    private static PACPollerService pacPollerService;
    private ProcessEntity genericProcessEntity;

    private final String clientNumber = "clientNumber";
    private final String eventSeqNumber = "eventSeqNumber";
    private final String computerSystemCd = "computerSystemCd";
    private final String eventTypeCode = "eventTypeCode";
    private final String csNum = "csNum";
    private final String surname = "surname";
    private final String givenName1 = "givenName1";
    private final String givenName2 = "givenName2";
    private final String birthDate = "birthDate";
    private final String gender = "gender";
    private final String photoGUID = "photoGUID";
    private final String probableDischargeDate = "probableDischargeDate";
    private final String pacLocationCd = "pacLocationCd";
    private final String outReason = "outReason";
    private final String computerSystemCd1 = "computerSystemCd";
    private final String isActive = "isActive";
    private final String sysDate = "sysDate";
    private final String fromCsNum = "fromCsNum";
    private final String userId = "userId";
    private final String mergeUserId = "mergeUserId";
    private final String icsLocationCd = "icsLocationCd";
    private final String isIn = "isIn";
    private final String custodyCenter = "custodyCenter";
    private final String livingUnit = "livingUnit";
    private final Client mockedClient =
            new Client(
                    new ProcessEntity(clientNumber, eventSeqNumber, computerSystemCd),
                    new EventEntity(clientNumber, eventSeqNumber, eventTypeCode),
                    mockRestTemplate,
                    mockOrdsProperties);

    private final DemographicsEntity actualDemographics =
            new DemographicsEntity(
                    clientNumber,
                    eventTypeCode,
                    csNum,
                    surname,
                    givenName1,
                    givenName2,
                    birthDate,
                    gender,
                    photoGUID,
                    probableDischargeDate,
                    outReason,
                    isActive,
                    fromCsNum,
                    mergeUserId,
                    livingUnit,
                    icsLocationCd,
                    isIn,
                    sysDate,
                    "avLocaCd",
                    "avClientLocaUserId",
                    "vLocaCdAlternate");

    @BeforeEach
    void beforeEachSetup() {
        MockitoAnnotations.openMocks(this);

        pacPollerService =
                new PACPollerService(
                        mockOrdsProperties,
                        null,
                        mockRestTemplate,
                        mockRabbitTemplate,
                        mockedAmqpAdmin,
                        null);
        when(mockOrdsProperties.getUsername()).thenReturn(testString);
        when(mockOrdsProperties.getPassword()).thenReturn(testString);
        when(mockOrdsProperties.getBaseUrl()).thenReturn(testUrl);
        when(mockOrdsProperties.getCmsIntBaseUrl()).thenReturn(testUrl);
        when(mockOrdsProperties.getCmsBaseUrl()).thenReturn(testUrl);
        when(mockOrdsProperties.getEventsEndpoint()).thenReturn(testEndpoint);
        when(mockOrdsProperties.getProcessesEndpoint()).thenReturn(testEndpoint);
        when(mockOrdsProperties.getEventsTypeEndpoint()).thenReturn(testEndpoint);
        when(mockOrdsProperties.getSuccessEndpoint()).thenReturn(testEndpoint);
        when(mockOrdsProperties.getSuccessEndpoint()).thenReturn(testEndpoint);
        when(mockOrdsProperties.getDemographicsEndpoint()).thenReturn(testEndpoint);

        genericProcessEntity = new ProcessEntity("1", "1", "1");

        when(mockedAmqpAdmin.declareQueue(any())).thenReturn(null);

        doNothing().when(mockRestTemplate).put(any(URI.class), any(UpdateEntryEntity.class));
    }

    @Test
    void pollOrdsForNewRecordsBringsZeroNewRecords(CapturedOutput output) {
        ResponseEntity<ProcessEntity[]> responseEntity =
                new ResponseEntity<>(new ProcessEntity[0], HttpStatus.OK);

        when(mockRestTemplate.exchange(
                        any(URI.class),
                        any(HttpMethod.class),
                        any(HttpEntity.class),
                        eq(ProcessEntity[].class)))
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
                        eq(EventsEntity.class)))
                .thenThrow(new RuntimeException("testing exception"));

        pacPollerService.pollOrdsForNewRecords();
        assertThat(output).contains("Failed to pull new records from the db");
    }

    @Test
    void pollOrdsForNewRecordsBringsNewRecords(CapturedOutput output) {
        ProcessEntity[] newEventsEntity = {genericProcessEntity, new ProcessEntity("2", "2", "2")};
        ResponseEntity<ProcessEntity[]> responseEntity =
                new ResponseEntity<>(newEventsEntity, HttpStatus.OK);

        when(mockRestTemplate.exchange(
                        any(URI.class),
                        any(HttpMethod.class),
                        any(HttpEntity.class),
                        eq(ProcessEntity[].class)))
                .thenReturn(responseEntity);

        when(mockRabbitTemplate.convertSendAndReceive(anyString(), anyString(), any(Client.class)))
                .thenReturn(null);

        pacPollerService.pollOrdsForNewRecords();
        assertThat(output).contains("Pulled " + newEventsEntity.length + " new records");
        verify(mockRabbitTemplate, times(1))
                .convertSendAndReceive(anyString(), anyString(), any(Client.class));
    }

    @Test
    void gettingEventTypeReturnsClientObject() {
        EventEntity eventEntity =
                new EventEntity("client id", "event sequence number", "event type code");
        ResponseEntity<EventEntity> responseEntity =
                new ResponseEntity<>(eventEntity, HttpStatus.OK);

        Client expectedClient = new Client(genericProcessEntity, eventEntity);

        when(mockRestTemplate.exchange(
                        any(URI.class),
                        any(HttpMethod.class),
                        any(HttpEntity.class),
                        eq(EventEntity.class)))
                .thenReturn(responseEntity);

        Client actualClient = pacPollerService.getEventForProcess(genericProcessEntity);
        assertEquals(expectedClient, actualClient);
    }

    @Test
    void problematicGettingEventTypeRaisesOrdsException() {
        when(mockRestTemplate.exchange(
                        any(URI.class),
                        any(HttpMethod.class),
                        any(HttpEntity.class),
                        eq(EventEntity.class)))
                .thenThrow(ORDSException.class);

        assertThrows(
                ORDSException.class,
                () -> pacPollerService.getEventForProcess(genericProcessEntity));
    }

    @SneakyThrows
    @ParameterizedTest
    @CsvSource(
            value = {
                "false,ca.bc.gov.open.pac.models.eventStatus.CompletedDuplicateEventStatus",
                "true,ca.bc.gov.open.pac.models.eventStatus.PendingEventStatus"
            })
    void clientOnCorrectStateDependingOnIfItHasOrNotANewerEvent(
            boolean hasNewerEvent, String eventStatusClassString) {

        Class<?> eventStatusClass = Class.forName(eventStatusClassString);
        mockedClient.setStatus(new PendingEventStatus(mockOrdsProperties, mockRestTemplate));

        NewerEventEntity newerEventEntity = new NewerEventEntity();
        newerEventEntity.setHasNewerEvent(hasNewerEvent);

        ResponseEntity<NewerEventEntity> responseEntity =
                new ResponseEntity<>(newerEventEntity, HttpStatus.OK);

        when(mockRestTemplate.getForObject(any(URI.class), eq(NewerEventEntity.class)))
                .thenReturn(newerEventEntity);

        Client actualClient = pacPollerService.getClientNewerSequence(mockedClient);
        assertEquals(eventStatusClass, actualClient.getStatus().getClass());
    }

    @Test
    void gettingDemographicsInfoFurtherPopulatesTheClientObject() {
        when(mockRestTemplate.getForObject(any(URI.class), eq(DemographicsEntity.class)))
                .thenReturn(actualDemographics);

        Client actualClient = pacPollerService.getDemographicsInfo(mockedClient);
        assertNotEquals(mockedClient, actualClient);
        assertEquals(new DemographicInfo(actualDemographics), actualClient.getDemographicInfo());
    }

    @Test
    void gettingNullResponseFromOrdsWhenDemographicsInfoThrowsOrdsException() {
        when(mockRestTemplate.getForObject(any(URI.class), eq(DemographicsEntity.class)))
                .thenReturn(null);

        assertThrows(ORDSException.class, () -> pacPollerService.getDemographicsInfo(mockedClient));
    }
}
