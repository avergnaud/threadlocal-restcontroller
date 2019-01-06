package hello;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.net.URL;
import java.util.Map;

import com.poc.local.LocalCounter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource
public class HelloControllerIT {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void getHello() throws Exception {

        ResponseEntity<String> response = null;

        for(int i=0; i<1000; i++) {
            response = template.getForEntity(base.toString(),
                    String.class);
        }

        assertThat(response.getBody(), equalTo("Greetings from Spring Boot!"));

        int total = 0;
        for (Map.Entry<String, Integer> entry : LocalCounter.log.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
            total += entry.getValue();
        }
        System.out.println(total);
    }
}