package ca.bc.gov.open.pac.models.dateFormatters;

import ca.bc.gov.open.pac.models.PacPropertiesInterface;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PacDateFormatter implements DateFormatterInterface {
    private final String pacDateFormat;
    private final String cmsDateFormat;

    public PacDateFormatter(PacPropertiesInterface pacProperties) {
        this.pacDateFormat = pacProperties.getPacDatePattern();
        this.cmsDateFormat = pacProperties.getCmsDatePattern();
    }

    @Override
    public String format(String dateString) {
        if (dateString == null || dateString.isBlank()) return dateString;

        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(cmsDateFormat));

        return date.format(DateTimeFormatter.ofPattern(pacDateFormat));
    }
}
