package ca.bc.gov.open.pac.models.eventTypeCode;

import ca.bc.gov.open.pac.models.Client;

public class CrelSynchronizeClientEntity extends SynchronizeClientEntity {

    public CrelSynchronizeClientEntity(Client client) {
        csNumber = client.getDemographicInfo().getCsNum();
    }
}
