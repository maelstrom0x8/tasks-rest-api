package com.rhine.taskmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;

import com.rhine.redstorm.RedstormApplication;
import com.rhine.redstorm.TestConfig;
import com.rhine.taskmanager.domain.dto.TaskListResponse;
import com.rhine.taskmanager.service.TaskManagerService;

@SpringBootTest(classes = { RedstormApplication.class, TestConfig.class })
@AutoConfigureMockMvc
public class TaskManagerServiceTest {

    @Autowired
    private TaskManagerService tms;

    @Test
    @WithUserDetails("anna")
    public void testListIsCreatedForAuthenticatedUser() {
        TaskListResponse list = tms.createList("default");

        assertEquals("default", list.getTitle());

    }

}
