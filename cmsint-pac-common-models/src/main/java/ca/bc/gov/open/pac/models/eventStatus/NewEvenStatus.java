package ca.bc.gov.open.pac.models.eventStatus;

import ca.bc.gov.open.pac.models.Client;
import org.springframework.web.client.RestTemplate;

public class NewEvenStatus extends EventStatus {

    public NewEvenStatus() {
        super();
    }

    public NewEvenStatus(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public Client updateToPending(Client client) {
        client.setEventStatus(new PendingEventStatus(restTemplate));

        updateStatusOnServer();

        return client;
    }

    @Override
    protected void updateStatusOnServer() {
        System.out.println(
                "Updating status to \"Pendind\" -, but for now I am only a print to the console"); // TODO - change the code to make a request
        throw new RuntimeException("aqui");
    }
}
