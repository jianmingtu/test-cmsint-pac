package ca.bc.gov.open.pac.models.eventTypeCode;

import ca.bc.gov.open.pac.models.Client;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@Data
@NoArgsConstructor
public class CrelSynchronizeClientEntity extends SynchronizeClientEntity {

    public CrelSynchronizeClientEntity(Client client) {
        csNumber = client.getDemographicInfo().getCsNum();
    }
}
