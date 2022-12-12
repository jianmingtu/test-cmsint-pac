package ca.bc.gov.open.pac.models.eventTypeCode;

import ca.bc.gov.open.pac.models.Client;

public class CimgSynchronizeClientEntity extends SynchronizeClientEntity {
    public CimgSynchronizeClientEntity(Client client) {
        csNumber = client.getDemographicInfo().getCsNum();
        photoGuid = client.getDemographicInfo().getPhotoGUID();
    }
}
