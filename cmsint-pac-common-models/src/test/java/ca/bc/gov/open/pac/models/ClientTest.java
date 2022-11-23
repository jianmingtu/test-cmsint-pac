package ca.bc.gov.open.pac.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ca.bc.gov.open.pac.models.ords.DemographicsEntity;
import ca.bc.gov.open.pac.models.ords.EventEntity;
import ca.bc.gov.open.pac.models.ords.ProcessEntity;
import org.junit.jupiter.api.Test;

class ClientTest {
    private final String clientNumber = "clientNumber";
    private final String eventSeqNumber = "eventSeqNumber";
    private final String computerSystemCd = "computerSystemCd";
    private final String eventTypeCode = "eventTypeCode";
    private final String csNum = "csNum";
    private final String surname = "surname";
    private final String givenName1 = "givenName1";
    private final String givenName2 = "givenName2";
    private final String birthDate = "birthDate";
    private final String gender = "gender";
    private final String photoGUID = "photoGUID";
    private final String probableDischargeDate = "probableDischargeDate";
    private final String pacLocationCd = "pacLocationCd";
    private final String outReason = "outReason";
    private final String computerSystemCd1 = "computerSystemCd";
    private final String isActive = "isActive";
    private final String sysDate = "sysDate";
    private final String fromCsNum = "fromCsNum";
    private final String userId = "userId";
    private final String mergeUserId = "mergeUserId";
    private final String icsLocationCd = "icsLocationCd";
    private final String isIn = "isIn";
    private final String custodyCenter = "custodyCenter";
    private final String livingUnit = "livingUnit";
    private final ProcessEntity processEntity =
            new ProcessEntity(clientNumber, eventSeqNumber, computerSystemCd);
    private final EventEntity eventEntity =
            new EventEntity(clientNumber, eventSeqNumber, eventTypeCode);
    private final Client actualClient = new Client(processEntity, eventEntity);
    private final DemographicsEntity actualDemographics =
            new DemographicsEntity(
                    clientNumber,
                    eventTypeCode,
                    csNum,
                    surname,
                    givenName1,
                    givenName2,
                    birthDate,
                    gender,
                    photoGUID,
                    probableDischargeDate,
                    outReason,
                    isActive,
                    fromCsNum,
                    mergeUserId,
                    livingUnit,
                    icsLocationCd,
                    isIn,
                    sysDate,
                    "avLocaCd",
                    "avClientLocaUserId",
                    "vLocaCdAlternate");

    @Test
    void constructorWithNewEventAndEventTypeCodeProperlyMapsTheValuesToTheFields() {
        EventEntity eventEntity =
                new EventEntity("client id", "event sequence number", "event type code");
        ProcessEntity processEntity = new ProcessEntity("1", "1", "1");
        Client expectedClient = new Client(processEntity, eventEntity);

        assertEquals(expectedClient.getClientNumber(), processEntity.getClientNumber());
        assertEquals(expectedClient.getEventSeqNum(), processEntity.getEventSeqNum());
        assertEquals(expectedClient.getComputerSystemCd(), processEntity.getComputerSystemCd());
        assertEquals(expectedClient.getEventTypeCode(), eventEntity.getEventTypeCode());
    }

    @Test
    void constructorWithClientAndDemographicsEntityGetTheRightValuesFromBoth() {
        Client expectedClient = new Client(actualClient, actualDemographics);




        assertEquals(expectedClient.getClientNumber(), actualClient.getClientNumber());
        assertEquals(expectedClient.getEventSeqNum(), actualClient.getEventSeqNum());
        assertEquals(expectedClient.getComputerSystemCd(), actualClient.getComputerSystemCd());
        assertEquals(expectedClient.getStatus(), actualClient.getStatus());
        assertEquals(expectedClient.getEventTypeCode(), actualClient.getEventTypeCode());

        assertEquals(expectedClient.getDemographicInfo().getCsNum(), actualDemographics.getCsNum());
        assertEquals(
                expectedClient.getDemographicInfo().getSurname(), actualDemographics.getSurname());
        assertEquals(
                expectedClient.getDemographicInfo().getGivenName1(),
                actualDemographics.getGivenName1());
        assertEquals(
                expectedClient.getDemographicInfo().getGivenName2(),
                actualDemographics.getGivenName2());
        assertEquals(
                expectedClient.getDemographicInfo().getBirthDate(),
                actualDemographics.getBirthDate());
        assertEquals(
                expectedClient.getDemographicInfo().getGender(), actualDemographics.getGender());
        assertEquals(
                expectedClient.getDemographicInfo().getPhotoGUID(),
                actualDemographics.getPhotoGUID());
        assertEquals(
                expectedClient.getDemographicInfo().getProbableDischargeDate(),
                actualDemographics.getProbableDischargeDate());
        assertEquals(
                expectedClient.getDemographicInfo().getOutReason(),
                actualDemographics.getOutReason());
        assertEquals(
                expectedClient.getDemographicInfo().getIsActive(),
                actualDemographics.getIsActive());
        assertEquals(
                expectedClient.getDemographicInfo().getFromCsNum(),
                actualDemographics.getFromCsNum());
        assertEquals(
                expectedClient.getDemographicInfo().getMergeUserId(),
                actualDemographics.getMergeUserId());
        assertEquals(
                expectedClient.getDemographicInfo().getLivingUnit(),
                actualDemographics.getLivingUnit());
        assertEquals(
                expectedClient.getDemographicInfo().getIcsLocationCd(),
                actualDemographics.getIcsLocationCd());
        assertEquals(expectedClient.getDemographicInfo().getIsIn(), actualDemographics.getIsIn());
        assertEquals(
                expectedClient.getDemographicInfo().getSysDate(), actualDemographics.getSysDate());

        //        Todo - assertions bellow should fail because I could not map the values properly
        assertNotNull(expectedClient.getDemographicInfo().getUserId());
        assertNotNull(expectedClient.getDemographicInfo().getCustodyCenter());
        assertNotNull(expectedClient.getDemographicInfo().getPacLocationCd());
    }
}
