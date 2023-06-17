package com.redstorm.rhine;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.redstorm.rhine.common.AbstractIntegrationTest;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class RedstormApplicationTests extends AbstractIntegrationTest {

    @Test
    void contextLoads() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/actuator/health")).andExpect(status().isOk());
    }
}
