package ca.bc.gov.open.pac.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.bc.gov.open.pac.models.eventStatus.NewEvenStatus;
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
                    "status",
                    new NewEvenStatus());

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
}
