package ca.bc.gov.open.pac.models;

public class NewEvenStatus extends EventStatus {
    @Override
    public Client updateToPending(Client client) {
        client.setEventStatus(new PendingEventStatus());

        updateStatusOnServer();

        return client;
    }

    @Override
    protected void updateStatusOnServer() {
        System.out.println("Updating status to \"Pendind\" -, but for now I am only a print to the console"); //TODO - change the code to make a request
    }
}
