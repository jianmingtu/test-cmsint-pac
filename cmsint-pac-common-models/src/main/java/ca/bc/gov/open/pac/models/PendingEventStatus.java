package ca.bc.gov.open.pac.models;

public class PendingEventStatus extends EventStatus {

    @Override
    protected void updateStatusOnServer() {
        System.out.println("Updating status to \"SOMETHING ELSE\" -, but for now I am only a print to the console"); //TODO - change the code to make a request
    }
}
