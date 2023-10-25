package org.example.receiptprocessor;

import org.example.receiptprocessor.controller.ReceiptController;
import org.example.receiptprocessor.dao.MemoryReceiptsDao;
import org.example.receiptprocessor.dao.ReceiptsDao;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(SpringRunner.class)
@WebMvcTest(ReceiptController.class)
public class ReceiptControllerTest {


    @Autowired
    private MockMvc mockMvc;

    ReceiptController controller;

    @Before
    public void setUp() throws Exception {
        System.out.println("Setup....");
        ReceiptsDao dao = new MemoryReceiptsDao();
        controller = new ReceiptController(dao);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    //    Tests endpoints
    @Test
    public void testTestEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/receipts/test")
                        .param("test", "testingParamValue")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Complete"));
    }
}