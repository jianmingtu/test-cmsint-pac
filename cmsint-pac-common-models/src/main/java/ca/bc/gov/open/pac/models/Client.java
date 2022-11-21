package ca.bc.gov.open.pac.models;

import ca.bc.gov.open.pac.models.eventStatus.EventStatus;
import ca.bc.gov.open.pac.models.eventStatus.NewEvenStatus;
import ca.bc.gov.open.pac.models.ords.EventEntity;
import ca.bc.gov.open.pac.models.ords.ProcessEntity;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client implements Serializable {
    private String clientNumber;
    private final String csNum;
    private final String eventSeqNum;
    private final String eventTypeCode;
    private final String surname;
    private final String givenName1;
    private final String givenName2;
    private final String birthDate;
    private final String gender;
    private final String photoGUID;
    private final String probableDischargeDate;
    private final String pacLocationCd;
    private final String outReason;
    private final String newerSequence;
    private final String computerSystemCd;
    private final String isActive;
    private final String sysDate;
    private final String fromCsNum;
    private final String userId;
    private final String mergeUserId;
    private final String icsLocationCd;
    private final String isIn;
    private final String custodyCenter;
    private final String livingUnit;
    // to accept the status if update process cancels
    private final String status;

    private EventStatus eventStatus;

    public Client(ProcessEntity processEntity, EventEntity eventEntity) {
        clientNumber = processEntity.getClientNumber();
        eventSeqNum = processEntity.getEventSeqNum();
        computerSystemCd = processEntity.getComputerSystemCd();
        eventTypeCode = eventEntity.getEventTypeCode();
        csNum = null;
        surname = null;
        givenName1 = null;
        givenName2 = null;
        birthDate = null;
        gender = null;
        photoGUID = null;
        probableDischargeDate = null;
        pacLocationCd = null;
        outReason = null;
        newerSequence = null;
        isActive = null;
        sysDate = null;
        fromCsNum = null;
        userId = null;
        mergeUserId = null;
        icsLocationCd = null;
        isIn = null;
        custodyCenter = null;
        livingUnit = null;
        status = null;
        eventStatus = new NewEvenStatus();
    }
}
