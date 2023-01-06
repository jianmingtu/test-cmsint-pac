package ca.bc.gov.open.jag.pac.extractor.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class OrdsPropertiesInterfaceTest {

    private final OrdsProperties ordsProperties = new OrdsProperties();

    private final String cmsIntOrdsUrl = "http://test.com/cmsint/";
    private final String cmsOrdsUrl = "http://test.com/cms/";
    private final String modulePath = "module/";

    @Test
    void getCmsIntBaseUrl() {
        var actual = ordsProperties.getCmsIntBaseUrl();
        assertEquals(cmsIntOrdsUrl + modulePath, actual);
    }

    @Test
    void getCmsBaseUrl() {
        var actual = ordsProperties.getCmsBaseUrl();
        assertEquals(cmsOrdsUrl + modulePath, actual);
    }
}
