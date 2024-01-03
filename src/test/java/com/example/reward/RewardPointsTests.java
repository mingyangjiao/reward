package com.example.reward;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RewardPointsTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private ObjectMapper objectMapper;

    private Long customerID;
    private String requestURL = "/api/reward/";

    // Test case for customer with purchase in all three months
    // earn point in two months
    // didn' earn point in one month
    @Test
    void customer1() throws Exception {

        customerID = 1L;
        ResponseEntity<String> responseEntity = template.getForEntity(requestURL + customerID, String.class);

        JsonNode jsonNode = objectMapper.readTree(responseEntity.getBody());

        assertEquals(customerID, jsonNode.get(0).get("customerID").asLong());
        assertEquals(10, jsonNode.get(0).get("month").asInt());
        assertEquals(3, jsonNode.get(0).get("totalPoints").asInt());

        assertEquals(customerID, jsonNode.get(1).get("customerID").asLong());
        assertEquals(12, jsonNode.get(1).get("month").asInt());
        assertEquals(90, jsonNode.get(1).get("totalPoints").asInt());
    }

    // Test case for customer with purchase more than $50 but below $100
    @Test
    void customer2() throws Exception {

        customerID = 2L;
        ResponseEntity<String> responseEntity = template.getForEntity(requestURL + customerID, String.class);

        JsonNode jsonNode = objectMapper.readTree(responseEntity.getBody());

        assertEquals(customerID, jsonNode.get(0).get("customerID").asLong());
        assertEquals(12, jsonNode.get(0).get("month").asInt());
        assertEquals(30, jsonNode.get(0).get("totalPoints").asInt());
    }

    // Test case for customer earn point in all three months
    @Test
    void customer3() throws Exception {

        customerID = 3L;
        ResponseEntity<String> responseEntity = template.getForEntity(requestURL + customerID, String.class);

        JsonNode jsonNode = objectMapper.readTree(responseEntity.getBody());

        assertEquals(customerID, jsonNode.get(0).get("customerID").asLong());
        assertEquals(10, jsonNode.get(0).get("month").asInt());
        assertEquals(90, jsonNode.get(0).get("totalPoints").asInt());

        assertEquals(customerID, jsonNode.get(1).get("customerID").asLong());
        assertEquals(11, jsonNode.get(1).get("month").asInt());
        assertEquals(130, jsonNode.get(1).get("totalPoints").asInt());

        assertEquals(customerID, jsonNode.get(2).get("customerID").asLong());
        assertEquals(12, jsonNode.get(2).get("month").asInt());
        assertEquals(170, jsonNode.get(2).get("totalPoints").asInt());

    }

    // Test case for customer with purchase but didn't earn any point
    @Test
    void customer4() throws Exception {

        customerID = 4L;
        ResponseEntity<String> responseEntity = template.getForEntity(requestURL + customerID, String.class);

        JsonNode jsonNode = objectMapper.readTree(responseEntity.getBody());

        assertTrue(jsonNode.isEmpty());

    }

    // Test case for customer with purchase with exact $100
    @Test
    void customer5() throws Exception {

        customerID = 5L;
        ResponseEntity<String> responseEntity = template.getForEntity(requestURL + customerID, String.class);

        JsonNode jsonNode = objectMapper.readTree(responseEntity.getBody());

        assertEquals(customerID, jsonNode.get(0).get("customerID").asLong());
        assertEquals(12, jsonNode.get(0).get("month").asInt());
        assertEquals(50, jsonNode.get(0).get("totalPoints").asInt());
    }

    // test case for customerID with purchase in current month
    // transactions of past three months e.g. 2023.10.2 - 2024.1.2
    // so return result will be 4 months including the October November December and January
    // For October, transactions before 2023.10.2 should not be included
    @Test
    void customer6() throws Exception {
        customerID = 6L;
        ResponseEntity<String> responseEntity = template.getForEntity(requestURL + customerID, String.class);

        JsonNode jsonNode = objectMapper.readTree(responseEntity.getBody());

        assertEquals(customerID, jsonNode.get(0).get("customerID").asLong());
        assertEquals(1, jsonNode.get(0).get("month").asInt());
        assertEquals(100, jsonNode.get(0).get("totalPoints").asInt());

        assertEquals(customerID, jsonNode.get(1).get("customerID").asLong());
        assertEquals(10, jsonNode.get(1).get("month").asInt());
        assertEquals(50, jsonNode.get(1).get("totalPoints").asInt());

        assertEquals(customerID, jsonNode.get(2).get("customerID").asLong());
        assertEquals(11, jsonNode.get(2).get("month").asInt());
        assertEquals(50, jsonNode.get(2).get("totalPoints").asInt());

        assertEquals(customerID, jsonNode.get(3).get("customerID").asLong());
        assertEquals(12, jsonNode.get(3).get("month").asInt());
        assertEquals(50, jsonNode.get(3).get("totalPoints").asInt());

    }

    // test case for customerID didn't exist
    @Test
    void customer7() throws Exception {
        customerID = 286L;
        ResponseEntity<String> responseEntity = template.getForEntity(requestURL + customerID, String.class);

        JsonNode jsonNode = objectMapper.readTree(responseEntity.getBody());

        assertTrue(jsonNode.isEmpty());
    }

}
