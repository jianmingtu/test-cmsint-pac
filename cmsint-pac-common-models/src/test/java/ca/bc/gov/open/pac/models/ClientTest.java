package ca.bc.gov.open.pac.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import ca.bc.gov.open.pac.models.ords.EventEntity;
import ca.bc.gov.open.pac.models.ords.ProcessEntity;
import org.junit.jupiter.api.Test;

class ClientTest {
    final Client actualClient =
            new Client(
                    "clientNumber",
                    "csNum",
                    "eventSeqNum",
                    "eventTypeCode",
                    "surname",
                    "givenName1",
                    "givenName2",
                    "birthDate",
                    "gender",
                    "photoGUID",
                    "probableDischargeDate",
                    "pacLocationCd",
                    "outReason",
                    "newerSequence",
                    "computerSystemCd",
                    "isActive",
                    "sysDate",
                    "fromCsNum",
                    "userId",
                    "mergeUserId",
                    "icsLocationCd",
                    "isIn",
                    "custodyCenter",
                    "livingUnit",
                    "status");

    @Test
    void makeSureNewInstanceIsNotTheSameObjectAsTheCurrent() {
        Client expectedClient = actualClient.getCopy();
        assertEquals(expectedClient, expectedClient);
        assertNotSame(expectedClient, actualClient);
    }

    @Test
    void makeSureNewInstanceIsNotTheSameObjectAsTheCurrentButItHasNewEventTypeCode() {
        String newEventTypeCode = "newEventType";
        Client expectedClient = actualClient.getCopyWithNewEventTypeCode(newEventTypeCode);

        assertNotSame(expectedClient, actualClient);
        assertEquals(expectedClient.getEventTypeCode(), newEventTypeCode);
    }

    @Test
    void constructorWithNewEventAndEventTypeCodeProperlyMapsTheValuesToTheFields() {
        EventEntity eventEntity = new EventEntity("code");
        ProcessEntity processEntity = new ProcessEntity("1", "1", "1");
        Client expectedClient = new Client(processEntity, eventEntity);

        assertEquals(expectedClient.getClientNumber(), processEntity.getClientNumber());
        assertEquals(expectedClient.getEventSeqNum(), processEntity.getEventSeqNum());
        assertEquals(expectedClient.getComputerSystemCd(), processEntity.getComputerSystemCd());
        assertEquals(expectedClient.getEventTypeCode(), eventEntity.getEventTypeCode());
    }
}
