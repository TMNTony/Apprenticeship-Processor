package org.example.receiptprocessor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.receiptprocessor.controller.ReceiptController;
import org.example.receiptprocessor.dao.MemoryReceiptsDao;
import org.example.receiptprocessor.dao.ReceiptsDao;
import org.example.receiptprocessor.models.Item;
import org.example.receiptprocessor.models.Receipt;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RunWith(SpringRunner.class)
@WebMvcTest(ReceiptController.class)
public class ReceiptControllerTest {


    @Autowired
    private MockMvc mockMvc;

    ReceiptController controller;

    @Before
    //    Arrange
    public void setUp() throws Exception {
        System.out.println("Setup....");
        ReceiptsDao dao = new MemoryReceiptsDao();
        controller = new ReceiptController(dao);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    //    Tests endpoints
    @Test
    public void test_endpoint() throws Exception {
        //        Act
        mockMvc.perform(MockMvcRequestBuilders.get("/receipts/test")
                        .param("test", "testingParamValue")
                        .contentType(MediaType.APPLICATION_JSON))
                //                Assert
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Complete"));
    }

    //  Tests that submitted receipt returns valid UUID
    @Test
    public void submit_receipt() throws Exception {
        //        Arrange
        MvcResult result = submitReceipt();
        String id = getUuid(result);

        //        Act
        try {
            UUID uuid = UUID.fromString(id);
            // Assert that 'id' is a valid UUID
            //         Assert
            Assert.assertTrue(true);
        } catch (IllegalArgumentException e) {
            // Handle the case where 'id' is not a valid UUID
            Assert.fail("id is not a valid UUID: " + id);
        }
    }

    //  Tests that valid UUID will return correct amount of points
    @Test
    public void get_points() throws Exception {
        //        Arrange
        MvcResult result = submitReceipt();
        String uuid = getUuid(result);
        //        Act
        MvcResult pointsResult = mockMvc.perform(MockMvcRequestBuilders.get("/receipts/{uuid}/points", uuid))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        int points = convertPoints(pointsResult);
        //        Assert
        Assert.assertEquals(103, points);
    }

    //    Create Recipe object for submission
    public Receipt createReceipt() {
        Receipt testReceipt = new Receipt();
        testReceipt.setRetailer("Target");
        testReceipt.setPurchaseDate("2022-01-01");
        testReceipt.setPurchaseTime("13:01");
        Item item1 = new Item("Mountain Dew 12PK", "6.49");
        Item item2 = new Item("Emils Cheese Pizza", "12.25");
        Item item3 = new Item("Knorr Creamy Chicken", "1.26");
        Item item4 = new Item("Doritos Nacho Cheese", "3.35");
        Item item5 = new Item("   Klarbrunn 12-PK 12 FL OZ  ", "12.00");
        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        testReceipt.setItems(items);

        return testReceipt;

    }

    //  Submits receipt
    public MvcResult submitReceipt() throws Exception {
        Receipt testReceipt = createReceipt();
        // Convert the testReceipt to a JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonTestReceipt = objectMapper.writeValueAsString(testReceipt);

        return mockMvc.perform(MockMvcRequestBuilders.post("/receipts/process")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonTestReceipt))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    //  Parses return object and extract UUID as a string
    public String getUuid(MvcResult result) throws UnsupportedEncodingException, JsonProcessingException {
        String response = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response);

        String id = jsonNode.get("id").asText();

        return id;
    }

    //  Parses return object and extracts points as an integer
    public int convertPoints(MvcResult result) throws UnsupportedEncodingException, JsonProcessingException {
        String response = result.getResponse().getContentAsString();


        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response);

        int points = jsonNode.get("points").asInt();

        return points;
    }
}