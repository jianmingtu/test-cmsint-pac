package ca.bc.gov.open.pac.loader;

import ca.bc.gov.open.pac.models.Client;

public interface EventLoader {

    void process(Client client);
}
