package org.aeros.tasks;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.aeros.tasks.common.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class ApplicationTests extends AbstractIntegrationTest {

    @Test
    void contextLoads() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/actuator/health")).andExpect(status().isOk());
    }
}
