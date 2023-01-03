package ca.bc.gov.open.pac.models.dateFormatters;

import ca.bc.gov.open.pac.models.PacPropertiesInterface;

public abstract class DateFormatterTest {

    protected final PacPropertiesInterface pacProperties = new PacProperties();

    protected static class PacProperties implements PacPropertiesInterface {

        @Override
        public String getPacDatePattern() {
            return "dd/MM/yyyy";
        }

        @Override
        public String getCmsDatePattern() {
            return "yyyy-MM-dd";
        }

        @Override
        public String getIcsDatePattern() {
            return "dd/MM/yyyy";
        }
    }
}
