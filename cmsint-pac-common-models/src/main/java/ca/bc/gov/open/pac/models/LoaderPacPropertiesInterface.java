package ca.bc.gov.open.pac.models;

public interface LoaderPacPropertiesInterface {

    String getPacQueue();

    String getPacRoutingKey();

    String getExchangeName();

    String getServiceUrl();
}
