package ca.bc.gov.open.pac.models.eventStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CompletedDuplicateEventStatusTest extends EventStatusTest {

    @Override
    EventStatus gettingTestInstance() {
        return new CompletedDuplicateEventStatus(mockOrdsProperties, mockedRestTemplate);
    }

    @Test
    @Override
    void gettingMethodNameReturnsExpectedValue() {
        assertEquals(CompletedDuplicateEventStatus.METHOD_NAME, eventStatus.getMethodName());
    }
}
