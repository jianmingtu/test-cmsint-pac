package ca.bc.gov.open.pac.models.eventStatus;

public enum EventStatusCode {
    PENDING("PEND"),
    IN_PROGRESS("INPROG"),
    COMPLETED_OK("COMOK"),
    COMPLETED_DUPLICATE("COMDUP"),
    CONNECTION_ERROR("CONERR"),
    APPLICATION_ERROR("APPERR"),
    IGNORE("IGNORE"),
    NEW("NEW");

    private final String code;

    EventStatusCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
