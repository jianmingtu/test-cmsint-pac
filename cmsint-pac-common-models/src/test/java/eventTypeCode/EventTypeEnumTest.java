package eventTypeCode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.bc.gov.open.pac.models.Client;
import ca.bc.gov.open.pac.models.eventTypeCode.CadmSynchronizeClient;
import ca.bc.gov.open.pac.models.eventTypeCode.CdemSynchronizeClient;
import ca.bc.gov.open.pac.models.eventTypeCode.CimgSynchronizeClient;
import ca.bc.gov.open.pac.models.eventTypeCode.CkeySynchronizeClient;
import ca.bc.gov.open.pac.models.eventTypeCode.ClocSynchronizeClient;
import ca.bc.gov.open.pac.models.eventTypeCode.ClunSynchronizeClient;
import ca.bc.gov.open.pac.models.eventTypeCode.CrelSynchronizeClient;
import ca.bc.gov.open.pac.models.eventTypeCode.EventTypeEnum;
import ca.bc.gov.open.pac.models.eventTypeCode.SynchronizeClient;
import ca.bc.gov.open.pac.models.ords.EventEntity;
import ca.bc.gov.open.pac.models.ords.ProcessEntity;
import org.junit.jupiter.api.Test;

class EventTypeEnumTest {
    final Client client = new Client(new ProcessEntity(), new EventEntity());

    @Test
    void cadmEventCodeProducesCadmSynchronizeClient() {

        CadmSynchronizeClient expectedClient = new CadmSynchronizeClient(client);
        SynchronizeClient actualClient = EventTypeEnum.CADM.getSynchronizeClient(client);

        assertEquals(expectedClient, actualClient);
    }

    @Test
    void cdemEventCodeProducesCdemSynchronizeClient() {

        CdemSynchronizeClient expectedClient = new CdemSynchronizeClient(client);
        SynchronizeClient actualClient = EventTypeEnum.CDEM.getSynchronizeClient(client);

        assertEquals(expectedClient, actualClient);
    }

    @Test
    void cimgEventCodeProducesCimgSynchronizeClient() {

        CimgSynchronizeClient expectedClient = new CimgSynchronizeClient(client);
        SynchronizeClient actualClient = EventTypeEnum.CIMG.getSynchronizeClient(client);

        assertEquals(expectedClient, actualClient);
    }

    @Test
    void ckeyEventCodeProducesCkeySynchronizeClient() {

        CkeySynchronizeClient expectedClient = new CkeySynchronizeClient(client);
        SynchronizeClient actualClient = EventTypeEnum.CKEY.getSynchronizeClient(client);

        assertEquals(expectedClient, actualClient);
    }

    @Test
    void clocEventCodeProducesClocSynchronizeClient() {

        ClocSynchronizeClient expectedClient = new ClocSynchronizeClient(client);
        SynchronizeClient actualClient = EventTypeEnum.CLOC.getSynchronizeClient(client);

        assertEquals(expectedClient, actualClient);
    }

    @Test
    void clunEventCodeProducesClunSynchronizeClient() {

        ClunSynchronizeClient expectedClient = new ClunSynchronizeClient(client);
        SynchronizeClient actualClient = EventTypeEnum.CLUN.getSynchronizeClient(client);

        assertEquals(expectedClient, actualClient);
    }

    @Test
    void crelEventCodeProducesClunSynchronizeClient() {

        CrelSynchronizeClient expectedClient = new CrelSynchronizeClient(client);
        SynchronizeClient actualClient = EventTypeEnum.CREL.getSynchronizeClient(client);

        assertEquals(expectedClient, actualClient);
    }
}
