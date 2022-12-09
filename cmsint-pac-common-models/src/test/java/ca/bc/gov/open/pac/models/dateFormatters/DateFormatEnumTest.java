package ca.bc.gov.open.pac.models.dateFormatters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DateFormatEnumTest extends DateFormatterTest {

    private final String packagePath = "ca.bc.gov.open.pac.models.dateFormatters.";

    @ParameterizedTest
    @CsvSource({"ICS,IcsDateFormatter", "PAC,PacDateFormatter"})
    void getDateFormatter(String name, String clazzName) throws ClassNotFoundException {
        DateFormatterInterface dateFormatter =
                DateFormatEnum.valueOf(name).getDateFormatter(pacProperties);

        Class<?> clazz = Class.forName(packagePath + clazzName);

        assertInstanceOf(clazz, dateFormatter);
    }
}
