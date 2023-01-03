package eventTypeCode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.bc.gov.open.pac.models.Client;
import ca.bc.gov.open.pac.models.eventTypeCode.*;
import ca.bc.gov.open.pac.models.eventTypeCode.SynchronizeClientEntity;
import ca.bc.gov.open.pac.models.ords.EventEntity;
import ca.bc.gov.open.pac.models.ords.ProcessEntity;
import org.junit.jupiter.api.Test;

class EventTypeEnumTest {
    final Client client = new Client(new ProcessEntity(), new EventEntity());

    @Test
    void cadmEventCodeProducesCadmSynchronizeClient() {

        CadmSynchronizeClientEntity expectedClient = new CadmSynchronizeClientEntity(client);
        SynchronizeClientEntity actualClient = EventTypeEnum.CADM.getSynchronizeClient(client);

        assertEquals(expectedClient, actualClient);
    }

    @Test
    void cdemEventCodeProducesCdemSynchronizeClient() {

        CdemSynchronizeClientEntity expectedClient = new CdemSynchronizeClientEntity(client);
        SynchronizeClientEntity actualClient = EventTypeEnum.CDEM.getSynchronizeClient(client);

        assertEquals(expectedClient, actualClient);
    }

    @Test
    void cimgEventCodeProducesCimgSynchronizeClient() {

        CimgSynchronizeClientEntity expectedClient = new CimgSynchronizeClientEntity(client);
        SynchronizeClientEntity actualClient = EventTypeEnum.CIMG.getSynchronizeClient(client);

        assertEquals(expectedClient, actualClient);
    }

    @Test
    void ckeyEventCodeProducesCkeySynchronizeClient() {

        CkeySynchronizeClientEntity expectedClient = new CkeySynchronizeClientEntity(client);
        SynchronizeClientEntity actualClient = EventTypeEnum.CKEY.getSynchronizeClient(client);

        assertEquals(expectedClient, actualClient);
    }

    @Test
    void clocEventCodeProducesClocSynchronizeClient() {

        ClocSynchronizeClientEntity expectedClient = new ClocSynchronizeClientEntity(client);
        SynchronizeClientEntity actualClient = EventTypeEnum.CLOC.getSynchronizeClient(client);

        assertEquals(expectedClient, actualClient);
    }

    @Test
    void clunEventCodeProducesClunSynchronizeClient() {

        ClunSynchronizeClientEntity expectedClient = new ClunSynchronizeClientEntity(client);
        SynchronizeClientEntity actualClient = EventTypeEnum.CLUN.getSynchronizeClient(client);

        assertEquals(expectedClient, actualClient);
    }

    @Test
    void crelEventCodeProducesClunSynchronizeClient() {

        CrelSynchronizeClientEntity expectedClient = new CrelSynchronizeClientEntity(client);
        SynchronizeClientEntity actualClient = EventTypeEnum.CREL.getSynchronizeClient(client);

        assertEquals(expectedClient, actualClient);
    }
}
