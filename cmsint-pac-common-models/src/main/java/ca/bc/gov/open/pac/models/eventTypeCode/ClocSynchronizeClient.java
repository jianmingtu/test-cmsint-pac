package ca.bc.gov.open.pac.models.eventTypeCode;

import ca.bc.gov.open.pac.models.Client;
import lombok.Getter;

@Getter
public class ClocSynchronizeClient extends SynchronizeClient {

    public ClocSynchronizeClient(Client client) {
        csNumber = client.getDemographicInfo().getCsNum();
        outLocation = client.getDemographicInfo().getPacLocationCd();
        outReason = client.getDemographicInfo().getOutReason();
    }
}
