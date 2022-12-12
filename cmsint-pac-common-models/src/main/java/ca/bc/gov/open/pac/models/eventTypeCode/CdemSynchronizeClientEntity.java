package ca.bc.gov.open.pac.models.eventTypeCode;

import ca.bc.gov.open.pac.models.Client;

public class CdemSynchronizeClientEntity extends SynchronizeClientEntity {

    public CdemSynchronizeClientEntity(Client client) {
        csNumber = client.getDemographicInfo().getCsNum();
    }
}
