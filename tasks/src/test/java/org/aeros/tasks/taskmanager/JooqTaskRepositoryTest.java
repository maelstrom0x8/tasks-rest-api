package org.aeros.tasks.taskmanager;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.aeros.tasks.common.AbstractIntegrationTest;
import org.aeros.tasks.taskmanager.domain.model.Task;
import org.aeros.tasks.taskmanager.domain.model.TaskList;
import org.aeros.tasks.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

@Sql("/test-data.sql")
public class JooqTaskRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void initialListCount() {
        List<TaskList> lauraLists = taskRepository.findAll("laura");
        List<TaskList> annaLists = taskRepository.findAll("anna");
        List<TaskList> harryLists = taskRepository.findAll("harry");

        assertThat(lauraLists.size()).isEqualTo(1);
        assertThat(annaLists.size()).isEqualTo(2);
        assertThat(harryLists.size()).isEqualTo(1);
    }

    @Test
    void shouldCreateListsSuccessfully() {
        TaskList saved = taskRepository.save(new TaskList(null, "Daily Routine", "harry"));

        assertThat(saved.id()).isNotNull();
        assertThat(saved.title()).isEqualTo("Daily Routine");
        assertThat(saved.owner()).isEqualTo("harry");
    }

    @Test
    void fetchTasksInAList() {
        TaskList list = taskRepository.findAll("laura").stream().findAny().orElseThrow();
        List<Task> tasks = taskRepository.findAllTasks("anna", list.id());

        assertThat(tasks.size()).isEqualTo(3);
    }
}
