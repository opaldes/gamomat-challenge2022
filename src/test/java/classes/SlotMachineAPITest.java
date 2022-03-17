package classes;

import gamomat.SlotMachineApp;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.boot.SpringApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class SlotMachineAPITest {
    @BeforeAll
    public static void setup(){
        SlotMachineApp.main(new String[0]);
    }

    @Test
    @DisplayName("Api needs to be reachable")
    public void reachableApi() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet get = new HttpGet("http://localhost:8080/spin");
        CloseableHttpResponse response = client.execute(get);
        response.close();

        Assertions.assertEquals(200, response.getStatusLine().getStatusCode());
    }

    @Test
    @DisplayName("Api endpoint for the machine response with expected values")
    public void reachableApiEndpointResponse() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet get = new HttpGet("http://localhost:8080/spin");
        CloseableHttpResponse response = client.execute(get);
        BufferedReader br = new BufferedReader(new InputStreamReader( response.getEntity().getContent()));
        String line;
        StringBuilder stringBuffer = new StringBuilder();
        while ((line = br.readLine()) != null) {
            stringBuffer.append(line);
        }
        JSONObject json = new JSONObject(stringBuffer.toString());

        Assertions.assertTrue(json.has("winAmount"));
        Assertions.assertTrue(json.has("winSubset"));
        Assertions.assertTrue(json.has("wonLines"));
    }

    @AfterAll
    public static void clear() {
        SpringApplication.exit(SlotMachineApp.context);
    }
}