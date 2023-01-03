package ca.bc.gov.open.pac.models.dateFormatters;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class IcsDateFormatterTest extends DateFormatterTest {
    private final IcsDateFormatter icsDateFormatter = new IcsDateFormatter(pacProperties);

    @Test
    void formatChangesDatePattern() {
        String actual = icsDateFormatter.format("2022-12-09");
        assertEquals("09/12/2022", actual);
    }

    @Test
    void nullDateStringReturnNullValue() {
        String actual = icsDateFormatter.format(null);
        assertNull(actual);
    }

    @ParameterizedTest
    @CsvSource({"'',''", "' ',' ','\t','\t'"})
    void blankDateStringReturnEmptyValue(String dateString, String expected) {
        String actual = icsDateFormatter.format(dateString);
        assertEquals(expected, actual);
    }
}
