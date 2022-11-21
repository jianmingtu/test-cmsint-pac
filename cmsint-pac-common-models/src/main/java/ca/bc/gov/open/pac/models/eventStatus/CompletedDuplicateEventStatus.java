package ca.bc.gov.open.pac.models.eventStatus;

import ca.bc.gov.open.pac.models.Client;
import org.springframework.web.client.RestTemplate;

public class CompletedDuplicateEventStatus extends EventStatus {

    public CompletedDuplicateEventStatus(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public Client updateToCompletedDuplicate(Client client) {
        client.setEventStatus(new CompletedDuplicateEventStatus(restTemplate));

        updateStatusOnServer();

        return client;
    }

    @Override
    protected void updateStatusOnServer() {
        System.out.println(
                "Updating status to \"SOMETHING ELSE\" -, but for now I am only a print to the console"); // TODO - change the code to make a request

        throw new RuntimeException("aqui");
    }
}
