package ca.bc.gov.open.pac.models.eventTypeCode;

import ca.bc.gov.open.pac.models.Client;

public class CdemSynchronizeClient extends SynchronizeClient {

    public CdemSynchronizeClient(Client client) {
        csNumber = client.getDemographicInfo().getCsNum();
    }
}
