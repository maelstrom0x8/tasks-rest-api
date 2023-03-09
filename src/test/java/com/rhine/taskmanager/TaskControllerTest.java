package com.rhine.taskmanager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhine.redstorm.RedstormApplication;
import com.rhine.redstorm.TestConfig;
import com.rhine.taskmanager.domain.dto.TaskRequest;
import com.rhine.taskmanager.service.TaskManagerService;

@SpringBootTest(classes = { RedstormApplication.class, TestConfig.class })
@AutoConfigureMockMvc
public class TaskControllerTest {

        @Autowired
        private ObjectMapper objectMapper;

        @Autowired
        private MockMvc mvc;

        @Autowired
        private TaskManagerService tms;

        @Test
        @WithUserDetails("anna")
        public void testListIsCreatedForAuthenticatedUser() throws Exception {

                mvc.perform(post("/api/list/create").param("title", "mondays"))
                                .andExpect(status().isCreated());
        }

        @Test
        public void testListisNotCreatedForUnauthenticatedUsers() throws Exception {
                mvc.perform(post("/api/list/create").param("title", "bi-monthly"))
                                .andExpect(status().isUnauthorized());
        }

        @Test
        @WithUserDetails("anna")
        public void testAddTaskToList() throws Exception {
                var list = tms.createList("default");
                var task = new TaskRequest("meeting with the chavez",
                                "Discussing the next move for the business",
                                LocalDateTime.of(2023, 3, 25, 12, 32), false);

                String json = objectMapper.writeValueAsString(task);
                mvc.perform(post("/api/list/task/new")
                                .param("list", list.getId().toString())
                                .content(json)
                                .accept(MediaType.APPLICATION_JSON))
                   .andExpect(status().isCreated());
        }

}
