package ca.bc.gov.open.pac.models;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public abstract class EventStatus {

    public Client updateToPending(Client client){
        throw new RuntimeException("The status of the event cannot be updated to Pending");
    }

    protected abstract void updateStatusOnServer();

}
