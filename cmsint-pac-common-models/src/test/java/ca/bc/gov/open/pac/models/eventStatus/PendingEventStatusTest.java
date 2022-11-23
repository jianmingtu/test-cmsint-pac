package ca.bc.gov.open.pac.models.eventStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import ca.bc.gov.open.pac.models.Client;
import ca.bc.gov.open.pac.models.TestClientInitializer;
import org.junit.jupiter.api.Test;

class PendingEventStatusTest extends EventStatusTest {

    @Override
    EventStatus gettingTestInstance() {
        return new PendingEventStatus(mockOrdsProperties, mockedRestTemplate);
    }

    @Test
    @Override
    void gettingMethodNameReturnsExpectedValue() {
        assertEquals(PendingEventStatus.METHOD_NAME, eventStatus.getMethodName());
    }

    @Test
    @Override
    void updateToCompletedDuplicate() {
        var expectedClient = TestClientInitializer.getClientInstance();
        doNothing().when(mockedRestTemplate).put(any(), any());

        Client actualClient = eventStatus.updateToCompletedDuplicate(expectedClient);
        assertSame(expectedClient, actualClient);
    }
}
