package ca.bc.gov.open.pac.models.eventTypeCode;

import ca.bc.gov.open.pac.models.Client;

public class CrelSynchronizeClient extends SynchronizeClient {

    public CrelSynchronizeClient(Client client) {
        csNumber = client.getDemographicInfo().getCsNum();
    }
}
