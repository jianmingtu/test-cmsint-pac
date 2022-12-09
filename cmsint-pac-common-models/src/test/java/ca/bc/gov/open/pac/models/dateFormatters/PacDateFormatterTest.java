package ca.bc.gov.open.pac.models.dateFormatters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PacDateFormatterTest extends DateFormatterTest {

    private final PacDateFormatter pacDateFormatter = new PacDateFormatter(pacProperties);

    @Test
    void formatChangesDatePattern() {
        String actual = pacDateFormatter.format("2022-12-09");
        assertEquals("09/12/2022", actual);
    }

    @Test
    void nullDateStringReturnNullValue() {
        String actual = pacDateFormatter.format(null);
        assertNull(actual);
    }

    @ParameterizedTest
    @CsvSource({"'',''", "' ',' ','\t','\t'"})
    void blankDateStringReturnEmptyValue(String dateString, String expected) {
        String actual = pacDateFormatter.format(dateString);
        assertEquals(expected, actual);
    }
}
