package ca.bc.gov.open.pac.models.eventTypeCode;

import ca.bc.gov.open.pac.models.Client;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Data
@NoArgsConstructor
public class CkeySynchronizeClientEntity extends SynchronizeClientEntity {

    public CkeySynchronizeClientEntity(Client client) {
        csNumber = client.getDemographicInfo().getCsNum();
        probableDischargeDate = client.getDemographicInfo().getProbableDischargeDate();
    }
}
