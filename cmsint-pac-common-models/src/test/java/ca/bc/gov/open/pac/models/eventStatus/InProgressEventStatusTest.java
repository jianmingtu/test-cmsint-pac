package ca.bc.gov.open.pac.models.eventStatus;

import ca.bc.gov.open.pac.models.Client;
import ca.bc.gov.open.pac.models.TestClientInitializer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

class InProgressEventStatusTest extends EventStatusTest {

    @Override
    EventStatus gettingTestInstance() {
        return new InProgressEventStatus(mockOrdsProperties, mockedRestTemplate);
    }

    @Test
    @Override
    void gettingMethodNameReturnsExpectedValue() {
        assertEquals(InProgressEventStatus.METHOD_NAME, eventStatus.getMethodName());
    }

    @Test
    @Override
    void updateToCompletedOk() {
        var expectedClient = TestClientInitializer.getClientInstance();
        doNothing().when(mockedRestTemplate).put(any(), any());

        Client actualClient = eventStatus.updateToCompletedOk(expectedClient);
        assertSame(expectedClient, actualClient);
    }
}
