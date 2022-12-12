package ca.bc.gov.open.pac.models.eventTypeCode;

import ca.bc.gov.open.pac.models.Client;
import lombok.Getter;

@Getter
public class CadmSynchronizeClientEntity extends SynchronizeClientEntity {

    public CadmSynchronizeClientEntity(Client client) {
        csNumber = client.getDemographicInfo().getCsNum();
        surname = client.getDemographicInfo().getSurname();
        givenName1 = client.getDemographicInfo().getGivenName1();
        givenName2 = client.getDemographicInfo().getGivenName2();
        birthDate = client.getDemographicInfo().getBirthDate();
        gender = client.getDemographicInfo().getGender();
        photoGuid = client.getDemographicInfo().getPhotoGUID();
        probableDischargeDate = client.getDemographicInfo().getProbableDischargeDate();
        centre = client.getDemographicInfo().getCustodyCenter();
        livingUnit = client.getDemographicInfo().getLivingUnit();
    }
}
