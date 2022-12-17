package com.rhine.redstorm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rhine.redstorm.model.Task;
import com.rhine.redstorm.model.TaskList;
import com.rhine.redstorm.repository.TaskListRepository;
import com.rhine.redstorm.repository.TaskRepository;
import com.rhine.redstorm.service.TaskService;

@SpringBootTest
class RedstormApplicationTests {

	@Autowired
	private TaskService taskService;

	@Autowired
	private TaskListRepository taskListRepository;

	@Autowired
	private TaskRepository taskRepository;

	Optional<TaskList> daily;
	Optional<TaskList> mondays;

	@BeforeEach
	void setUp() {
		taskService.createList("Daily");
		taskService.createList("Mondays");

		daily = taskListRepository.findByName("Daily");
		mondays = taskListRepository.findByName("Mondays");

	}
  
	@AfterEach
	void tearDown() {
		taskListRepository.deleteAll();
	}

	@Test
	public void testTaskListCreate() {
		
		Optional<TaskList> d1 = taskListRepository.findByName("Daily");

		assertTrue(d1.isPresent());
		assertEquals("Daily", d1.get().getName());
	}

	@Test
	public void testNewTaskState() {
		Task task = new Task();
		task.setName("Workout");
		task.setList(daily.get());

		Task t1 = taskRepository.save(task);

		assertTrue(t1.getId() != null);
		
	}

	@Test
	public void testTaskAndListCardinality() {

		String taskName = "Brush";
		String desc = "Brushing routine for good oral health";
		LocalDateTime sched = LocalDateTime.now();

		Task task = Task.builder()
			.name(taskName).description(desc).schedule(sched)
			.build();

		TaskList list = taskService.createList("Tasks");

		taskService.addTask(list.getId(), task);

		Optional<Task> t0 = taskRepository.findByName(taskName);


		assertTrue(t0.isPresent());
		assertNotEquals(null, t0.get().getId());
		assertEquals(list.getId(), t0.get().getList().getId());

	}

}
