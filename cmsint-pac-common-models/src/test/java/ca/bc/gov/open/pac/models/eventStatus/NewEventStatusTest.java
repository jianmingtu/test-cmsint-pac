package ca.bc.gov.open.pac.models.eventStatus;

import ca.bc.gov.open.pac.models.Client;
import ca.bc.gov.open.pac.models.TestClientInitializer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

class NewEventStatusTest extends EventStatusTest {

    @Override
    EventStatus gettingTestInstance() {
        return new NewEventStatus(mockOrdsProperties, mockedRestTemplate);
    }

    @Test
    @Override
    void gettingMethodNameReturnsExpectedValue() {
        assertEquals(NewEventStatus.METHOD_NAME, eventStatus.getMethodName());
    }

    @Test
    @Override
    void updateToPending() {
        var expectedClient = TestClientInitializer.getClientInstance();
        doNothing()
                .when(mockedRestTemplate)
                .put(any(), any());

        Client actualClient = eventStatus.updateToPending(expectedClient);
        assertSame(expectedClient, actualClient);
    }
}
