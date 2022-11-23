package ca.bc.gov.open.pac.models.eventStatus;

import ca.bc.gov.open.pac.models.exceptions.ORDSException;
import ca.bc.gov.open.pac.models.ords.OrdsProperties;
import ca.bc.gov.open.pac.models.ords.UpdateEntryEntity;
import java.net.URI;
import static ca.bc.gov.open.pac.models.TestClientInitializer.getClientInstance;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

abstract class EventStatusTest {

    @Mock protected RestTemplate mockedRestTemplate;
    @Mock protected OrdsProperties mockOrdsProperties;
    protected EventStatus eventStatus;
    protected static final String testUrl = "http://example.com";
    protected static final String testEndpoint = "/endpoint";

    abstract void gettingMethodNameReturnsExpectedValue();
    abstract EventStatus gettingTestInstance();

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        eventStatus = gettingTestInstance();

        when(mockOrdsProperties.getCmsBaseUrl()).thenReturn(testUrl);
        when(mockOrdsProperties.getEntriesEndpoint()).thenReturn(testEndpoint);
    }

    @Test
    void updateToPending() {
        assertThrows(
                UnsupportedOperationException.class,
                () -> eventStatus.updateToPending(getClientInstance()));
    }

    @Test
    void updateToCompletedDuplicate() {
        assertThrows(
                UnsupportedOperationException.class,
                () -> eventStatus.updateToCompletedDuplicate(getClientInstance()));
    }

    @Test
    void updateStatusOnServerGetSuccessStatusBack() {
        doNothing().when(mockedRestTemplate).put(any(URI.class), any(UpdateEntryEntity.class));

        assertDoesNotThrow(
                () -> eventStatus.updateStatusOnServer(getClientInstance(), EventStatusCode.NEW));
    }

    @Test
    void updateStatusOnServerRaisesExceptionDueToFailure() {
        doThrow(new RuntimeException("test exception"))
                .when(mockedRestTemplate)
                .put(any(URI.class), any(UpdateEntryEntity.class));
        assertThrows(
                ORDSException.class,
                () -> eventStatus.updateStatusOnServer(getClientInstance(), EventStatusCode.NEW));
    }
}
