package at.refugeescode.Mp5_TheMarathon.view;

import at.refugeescode.Mp5_TheMarathon.model.Runner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.time.Duration;
import java.util.Arrays;
import java.util.List;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
class RunnersEndpointTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testTemplate;

    @SpyBean
    private RunnersEndpoint runnersEndpoint;

    private String endpoint = "runners";

    private String url;


    @BeforeEach
    void before() {
        url = "http://localhost:" + port + endpoint;
    }

    @Test
    void BringAll() {
        ResponseEntity <Runner[]> response = testTemplate.getForEntity(url, Runner[].class);
        List <Runner> theRunners = Arrays.asList(response.getBody());
        assertTrue(theRunners.isEmpty());
        verify(runnersEndpoint).bringAll();
    }

    @Test
    void Send() {
        Duration time = Duration.ofHours(2);
        Runner testRunner = new Runner("Rami", time);
        ResponseEntity <Runner> postResponse = testTemplate.postForEntity(url, testRunner, Runner.class);
        assertThat(testRunner, samePropertyValuesAs(postResponse.getBody()));

        ResponseEntity <Runner[]> response = testTemplate.getForEntity(url, Runner[].class);
        List <Runner> theRunners = Arrays.asList(response.getBody());
        assertFalse(theRunners.isEmpty());


    }

}