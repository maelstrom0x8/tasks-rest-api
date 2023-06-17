package com.redstorm.rhine.taskmanager;

import com.redstorm.rhine.common.AbstractIntegrationTest;
import com.redstorm.rhine.taskmanager.domain.model.TaskList;
import com.redstorm.rhine.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Sql("/test-data.sql")
public class JooqTaskRepositoryTest extends AbstractIntegrationTest {

    @Autowired private TaskRepository taskRepository;

    @Test
    void testInitialListsAndTasks() {
        List<TaskList> lauraLists = taskRepository.findAll("laura");
        List<TaskList> annaLists = taskRepository.findAll("anna");

        assertThat(lauraLists.size()).isEqualTo(1);
        assertThat(annaLists.size()).isEqualTo(2);
    }


}
