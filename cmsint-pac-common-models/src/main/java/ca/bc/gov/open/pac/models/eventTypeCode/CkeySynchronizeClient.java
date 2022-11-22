package ca.bc.gov.open.pac.models.eventTypeCode;

import ca.bc.gov.open.pac.models.Client;
import lombok.Getter;

@Getter
public class CkeySynchronizeClient extends SynchronizeClient {

    public CkeySynchronizeClient(Client client) {
        csNumber = client.getDemographicInfo().getCsNum();
        probableDischargeDate = client.getDemographicInfo().getProbableDischargeDate();
    }
}
