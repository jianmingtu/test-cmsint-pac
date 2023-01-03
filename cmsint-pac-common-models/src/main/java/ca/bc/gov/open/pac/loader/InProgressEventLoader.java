package ca.bc.gov.open.pac.loader;

import ca.bc.gov.open.pac.models.*;
import ca.bc.gov.open.pac.models.eventTypeCode.EventTypeEnum;
import ca.bc.gov.open.pac.models.eventTypeCode.SynchronizeClientEntity;
import ca.bc.gov.open.pac.models.exceptions.ORDSException;
import com.health.phis.ws.SynchronizeClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import java.io.Writer;
import java.io.StringWriter;

@Slf4j
public class InProgressEventLoader implements EventLoader {

    private final WebServiceTemplate webServiceTemplate;
    private final LoaderPacPropertiesInterface pacProperties;

    public InProgressEventLoader(
            WebServiceTemplate webServiceTemplate, LoaderPacPropertiesInterface pacProperties) {
        this.pacProperties = pacProperties;
        this.webServiceTemplate = webServiceTemplate;
    }
       private void invokeSoapService(SynchronizeClientEntity synchronizeClientEntity) {
        // Invoke Soap Service
        try {
            SynchronizeClient foo = synchronizeClientEntity.convertToSynchronizeClient();
            JAXBContext jaxbContext = JAXBContext.newInstance(foo.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(foo, stringWriter);
            stringWriter.toString();
////



            webServiceTemplate.marshalSendAndReceive(
                    pacProperties.getServiceUrl(), synchronizeClientEntity.convertToSynchronizeClient());
            log.info(new RequestSuccessLog("Request Success", "synchronizeClient").toString());
        } catch (Exception ex) {
            log.error(
                    new OrdsErrorLog(
                                    "Error received from SOAP SERVICE - synchronizeClient",
                                    "pacUpdate",
                                    ex.getMessage(),
                                    synchronizeClientEntity)
                            .toString());
            throw new ORDSException(synchronizeClientEntity.toString());
        }
    }

    private SynchronizeClientEntity getSynchronizeClientEntity(Client client) {
        // Compose Soap Service Request Body
        if (EventTypeEnum.hasValue(client.getEventTypeCode()))
            return EventTypeEnum.valueOf(client.getEventTypeCode()).getSynchronizeClient(client);

        throw new IllegalArgumentException(
                "Received EventTypeCode " + client.getEventTypeCode() + " is not expected");
    }

    @Override
    public void process(Client client) {
        var synchronizeClientEntity = getSynchronizeClientEntity(client);
        invokeSoapService(synchronizeClientEntity);
        client.getStatus().updateToCompletedOk(client);
    }
}
