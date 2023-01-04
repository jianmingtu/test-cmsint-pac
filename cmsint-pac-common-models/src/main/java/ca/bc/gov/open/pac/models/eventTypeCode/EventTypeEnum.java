package ca.bc.gov.open.pac.models.eventTypeCode;

import ca.bc.gov.open.pac.models.Client;
import com.health.phis.ws.SynchronizeClient;
import java.util.Arrays;
import java.util.function.Function;

public enum EventTypeEnum {
    CADM(CadmSynchronizeClientEntity::new),
    CDEM(CdemSynchronizeClientEntity::new),
    CREL(CrelSynchronizeClientEntity::new),
    CLUN(ClunSynchronizeClientEntity::new),
    CKEY(CkeySynchronizeClientEntity::new),
    CLOC(ClocSynchronizeClientEntity::new),
    CIMG(CimgSynchronizeClientEntity::new);

    private final Function<Client, SynchronizeClient> synchronizeClientFunction;

    private EventTypeEnum(Function<Client, SynchronizeClient> synchronizeClientFunction) {
        this.synchronizeClientFunction = synchronizeClientFunction;
    }

    public static boolean hasValue(String code) {
        return Arrays.stream(values())
                .anyMatch((EventTypeEnum eventType) -> eventType.name().equalsIgnoreCase(code));
    }

    public SynchronizeClient getSynchronizeClient(Client client) {
        return synchronizeClientFunction.apply(client);
    }
}
