package ca.bc.gov.open.jag.pac.extractor.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class OrdsPropertiesInterfaceTest {

    private final OrdsProperties ordsProperties = new OrdsProperties();

    private final String baseUrl = "http://test.com/";
    private final String cmsIntPath = "cmsint/";
    private final String cmsPath = "cms/";
    private final String modulePath = "module/";

    @Test
    void getCmsIntBaseUrl() {
        var actual = ordsProperties.getCmsIntBaseUrl();
        assertEquals(baseUrl + cmsIntPath + modulePath, actual);
    }

    @Test
    void getCmsBaseUrl() {
        var actual = ordsProperties.getCmsBaseUrl();
        assertEquals(baseUrl + cmsPath + modulePath, actual);
    }
}
