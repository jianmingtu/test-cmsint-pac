package ca.bc.gov.open.pac.models.ords;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProcessEntity extends BaseEntity implements Serializable {
    @JsonProperty("clientNumber")
    private String clientNumber;

    @JsonProperty("eventSeqNum")
    private String eventSeqNum;

    @JsonProperty("computerSystemCd")
    private String computerSystemCd;
}
