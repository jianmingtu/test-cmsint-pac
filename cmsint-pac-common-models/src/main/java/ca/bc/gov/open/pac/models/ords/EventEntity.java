package ca.bc.gov.open.pac.models.ords;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventEntity extends BaseEntity implements Serializable {
    @JsonProperty("clientId")
    private String clientId;

    @JsonProperty("eventSeqNum")
    private String eventSeqNum;

    @JsonProperty("eventTypeCode")
    private String eventTypeCode;
}
