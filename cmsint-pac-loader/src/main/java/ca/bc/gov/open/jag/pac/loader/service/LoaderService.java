package ca.bc.gov.open.jag.pac.loader.service;

import ca.bc.gov.open.jag.pac.loader.config.PacProperties;
import ca.bc.gov.open.pac.models.Client;
import ca.bc.gov.open.pac.models.OrdsErrorLog;
import ca.bc.gov.open.pac.models.RequestSuccessLog;
import ca.bc.gov.open.pac.models.eventStatus.InProgressEventStatus;
import ca.bc.gov.open.pac.models.eventTypeCode.EventTypeEnum;
import ca.bc.gov.open.pac.models.eventTypeCode.SynchronizeClientEntity;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
@Slf4j
public class LoaderService {

    private final WebServiceTemplate webServiceTemplate;
    private final PacProperties pacProperties;

    public LoaderService(WebServiceTemplate webServiceTemplate, PacProperties pacProperties) {
        this.webServiceTemplate = webServiceTemplate;
        this.pacProperties = pacProperties;
    }

    public void processPAC(Client client) {
        var status = client.getStatus().getClass();
        var expectedEventStatuses = Arrays.asList(InProgressEventStatus.class);
        if (!expectedEventStatuses.contains(status)) return;

        var synchronizeClientEntity = getSynchronizeClientEntity(client);
        invokeSoapService(synchronizeClientEntity);
        client.getStatus().updateToCompletedOk(client);
    }

    private void invokeSoapService(SynchronizeClientEntity synchronizeClientEntity) {
        // Invoke Soap Service
        try {
            webServiceTemplate.marshalSendAndReceive(
                    pacProperties.getServiceUrl(), synchronizeClientEntity);
            log.info(new RequestSuccessLog("Request Success", "synchronizeClient").toString());
        } catch (Exception ex) {
            log.error(
                    new OrdsErrorLog(
                                    "Error received from SOAP SERVICE - synchronizeClient",
                                    "pacUpdate",
                                    ex.getMessage(),
                                    synchronizeClientEntity)
                            .toString());
        }
    }

    private SynchronizeClientEntity getSynchronizeClientEntity(Client client) {
        // Compose Soap Service Request Body
        if (EventTypeEnum.hasValue(client.getEventTypeCode()))
            return EventTypeEnum.valueOf(client.getEventTypeCode()).getSynchronizeClient(client);

        throw new IllegalArgumentException(
                "Received EventTypeCode " + client.getEventTypeCode() + " is not expected");
    }
}
