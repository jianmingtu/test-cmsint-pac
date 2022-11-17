package ca.bc.gov.open.pac.models.ords;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class EventsEntity extends BaseEntity implements Serializable {
    private final List<ProcessEntity> newEventsEntity;

    public EventsEntity() {
        newEventsEntity = new ArrayList<>();
    }

    public <T> EventsEntity(List<ProcessEntity> newEventsEntity) {
        this.newEventsEntity = newEventsEntity;
    }
}
