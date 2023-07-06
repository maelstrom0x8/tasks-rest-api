package org.aeros.tasks.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.aeros.tasks.taskmanager.domain.model.TaskList;
import org.aeros.tasks.taskmanager.service.TaskManagerService;
import org.aeros.tasks.web.controller.TaskController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles({"test"})
@WebMvcTest(controllers = {TaskController.class})
public class TaskControllerTest {

    @MockBean
    private TaskManagerService taskManagerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser(setupBefore = TestExecutionEvent.TEST_EXECUTION)
    void testShouldReturnUserLists() throws Exception {
        when(taskManagerService.createList("Daily Routine")).thenReturn(new TaskList(1L, "Daily Routine", "anna"));

        mockMvc.perform(post("/api/lists"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1L"))
                .andExpect(jsonPath("$.title").value("Daily Routine"))
                .andExpect(jsonPath("$.owner").value("anna"));
    }
}
