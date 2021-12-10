package com.samueliheadindu.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.samueliheadindu.assignment.entity.Drone;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.http.HttpEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(scripts = "classpath:resources/data.sql")
public class DispatchControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    @Test
    public void testRegisterDroneSuccess() throws URISyntaxException {
        final String url = "http://localhost:" + randomServerPort + "/drones";
        URI uri = new URI(url);
        Drone drone = new Drone();
        drone.setSerialNumber("DKOIDLKDLSKAD");
        drone.setModel("Heavyweight");
        drone.setWeightLimit(500L);
        drone.setBatteryCapacity(100L);
        drone.setState("IDLE");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        HttpEntity<Drone> request = new HttpEntity<>(drone, headers);

        ResponseEntity<Drone> result = this.restTemplate.postForEntity(uri, request, Drone.class);

        assertEquals(201, result.getStatusCodeValue());
    }

    @Test
    public void testRetrieveDronesSuccess() throws URISyntaxException {
        final String url = "http://localhost:" + randomServerPort + "/drones";
        URI uri = new URI(url);

        ResponseEntity<Drone[]> result = this.restTemplate.getForEntity(uri, Drone[].class);
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void testRetrieveAvailableDronesSuccess() throws URISyntaxException {
        final String url = "http://localhost:" + randomServerPort + "/drones/available";
        URI uri = new URI(url);

        ResponseEntity<Drone[]> result = this.restTemplate.getForEntity(uri, Drone[].class);
        assertEquals(200, result.getStatusCodeValue());
    }
}
