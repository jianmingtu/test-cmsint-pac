package ca.bc.gov.open.pac.models.eventTypeCode;

import java.io.Serializable;

import com.health.phis.ws.SynchronizeClient;
import lombok.Data;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

@Data
public abstract class SynchronizeClientEntity implements Serializable {
    protected String csNumber;
    protected String surname;
    protected String givenName1;
    protected String givenName2;
    protected String birthDate;
    protected String gender;
    protected String photoGuid;
    protected String probableDischargeDate;
    protected String outLocation;
    protected String outReason;
    protected String centre;
    protected String livingUnit;

    public SynchronizeClient convertToSynchronizeClient(){
        SynchronizeClient synchronizeClient = new SynchronizeClient();

        synchronizeClient.setCsNumber(csNumber);
        synchronizeClient.setSurname(surname);
        synchronizeClient.setGivenName1(givenName1);

        synchronizeClient.setGivenName2(new JAXBElement<>(new QName("com.health.phis.ws.SynchronizeClient"),String.class,givenName2));
//        synchronizeClient.setBirthDate(new JAXBElement<>(new QName("birthDate"),String.class,birthDate));
//        synchronizeClient.setGender(new JAXBElement<>(new QName("gender"),String.class,gender));
//        synchronizeClient.setPhotoGuid(new JAXBElement<>(new QName("photoGuid"),String.class,photoGuid));
//        synchronizeClient.setProbableDischargeDate(new JAXBElement<>(new QName("probableDischargeDate"),String.class,probableDischargeDate));
//        synchronizeClient.setOutLocation(new JAXBElement<>(new QName("outLocation"),String.class,outLocation));
//        synchronizeClient.setOutReason(new JAXBElement<>(new QName("outReason"),String.class,outReason));
//        synchronizeClient.setCentre(new JAXBElement<>(new QName("centre"),String.class,centre));
//        synchronizeClient.setLivingUnit(new JAXBElement<>(new QName("livingUnit"),String.class,livingUnit));

        return synchronizeClient;
    }
}
