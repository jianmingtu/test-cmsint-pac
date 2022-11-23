package ca.bc.gov.open.pac.models.ords;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

class OrdsPropertiesTest {

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
