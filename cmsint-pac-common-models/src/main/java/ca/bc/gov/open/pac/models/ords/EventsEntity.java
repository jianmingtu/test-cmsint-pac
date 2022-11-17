package ca.bc.gov.open.pac.models.ords;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class EventsEntity extends BaseEntity implements Serializable {
    private final List<NewEventEntity> newEventsEntity;

    public EventsEntity() {
        newEventsEntity = new ArrayList<>();
    }

    public <T> EventsEntity(List<NewEventEntity> newEventsEntity) {
        this.newEventsEntity = newEventsEntity;
    }
}
