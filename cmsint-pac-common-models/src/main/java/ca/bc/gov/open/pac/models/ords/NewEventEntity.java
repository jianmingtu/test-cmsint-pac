package ca.bc.gov.open.pac.models.ords;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class NewEventEntity extends BaseEntity implements Serializable {
    @JsonProperty("clientNumber")
    private String clientNumber;
    @JsonProperty("eventSeqNum")
    private String eventSeqNum;
    @JsonProperty("computerSystemCd")
    private String computerSystemCd;
}
