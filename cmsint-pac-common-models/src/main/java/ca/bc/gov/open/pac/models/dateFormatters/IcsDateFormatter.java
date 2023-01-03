package ca.bc.gov.open.pac.models.dateFormatters;

import ca.bc.gov.open.pac.models.PacPropertiesInterface;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class IcsDateFormatter implements DateFormatterInterface {
    private final String icsDateFormat;
    private final String cmsDateFormat;

    public IcsDateFormatter(PacPropertiesInterface pacProperties) {
        this.icsDateFormat = pacProperties.getIcsDatePattern();
        this.cmsDateFormat = pacProperties.getCmsDatePattern();
    }

    @Override
    public String format(String dateString) {
        if (dateString == null || dateString.isBlank()) return dateString;

        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(cmsDateFormat));

        return date.format(DateTimeFormatter.ofPattern(icsDateFormat));
    }
}
