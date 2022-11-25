package ca.bc.gov.open.pac.models.eventTypeCode;

import ca.bc.gov.open.pac.models.Client;

public class CimgSynchronizeClient extends SynchronizeClient {
    public CimgSynchronizeClient(Client client) {
        csNumber = client.getDemographicInfo().getCsNum();
        photoGuid = client.getDemographicInfo().getPhotoGUID();
    }
}
