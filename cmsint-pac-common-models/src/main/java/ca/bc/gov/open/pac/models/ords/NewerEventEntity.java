package ca.bc.gov.open.pac.models.ords;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.*;

@RequiredArgsConstructor
@Setter
public class NewerEventEntity extends BaseEntity implements Serializable {
    @JsonProperty("hasNewerEvent")
    private boolean hasNewerEvent;

    public boolean hasNewerEvent() {
        return hasNewerEvent;
    }
}
