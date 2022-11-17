package ca.bc.gov.open.pac.models.ords;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventTypeCodeEntity extends BaseEntity implements Serializable {
    @JsonProperty("eventTypeCode")
    private String eventTypeCode;
}
