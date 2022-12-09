package ca.bc.gov.open.pac.models.dateFormatters;

import ca.bc.gov.open.pac.models.PacPropertiesInterface;

public enum DateFormatEnum {
    ICS {
        @Override
        public DateFormatterInterface getDateFormatter(PacPropertiesInterface pacProperties) {
            return new IcsDateFormatter(pacProperties);
        }
    },
    PAC {
        @Override
        public DateFormatterInterface getDateFormatter(PacPropertiesInterface pacProperties) {
            return new PacDateFormatter(pacProperties);
        }
    };

    public abstract DateFormatterInterface getDateFormatter(PacPropertiesInterface pacProperties);
}
