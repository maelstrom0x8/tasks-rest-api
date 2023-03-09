package com.rhine.taskmanager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import com.rhine.redstorm.RedstormApplication;
import com.rhine.redstorm.TestConfig;

@SpringBootTest(classes = { RedstormApplication.class, TestConfig.class })
@AutoConfigureMockMvc
public class TaskControllerTest {

        @Autowired
        private MockMvc mvc;

        @Test
        @WithUserDetails("anna")
        public void listIsCreatedForAuthenticatedUser() throws Exception {

                mvc.perform(post("/api/list/create").param("title", "mondays"))
                                .andExpect(status().isCreated());
        }

        @Test
        public void listisNotCreatedForUnauthenticatedUsers() throws Exception {
                mvc.perform(post("/api/list/create").param("title", "bi-monthly"))
                                .andExpect(status().isUnauthorized());
        }

}
