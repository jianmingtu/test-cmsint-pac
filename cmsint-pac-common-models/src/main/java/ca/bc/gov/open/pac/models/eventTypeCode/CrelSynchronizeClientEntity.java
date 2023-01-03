package ca.bc.gov.open.pac.models.eventTypeCode;

import ca.bc.gov.open.pac.models.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Data
@NoArgsConstructor
public class CrelSynchronizeClientEntity extends SynchronizeClientEntity {

    public CrelSynchronizeClientEntity(Client client) {
        csNumber = client.getDemographicInfo().getCsNum();
    }
}
