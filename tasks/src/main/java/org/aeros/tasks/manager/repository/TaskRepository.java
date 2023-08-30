package org.aeros.tasks.manager.repository;

import org.aeros.tasks.manager.domain.model.Task;
import org.aeros.tasks.manager.domain.model.TaskList;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    TaskList save(TaskList list);

    Task save(Long listID, Task task);

    List<TaskList> findAll(String owner);

    List<Task> findAll(Long listId);

    Optional<TaskList> findByOwner(String owner);

    TaskList findById(Long id);

    void deleteAll(String owner);

    void deleteList(Long listId, String user);

    void deleteAllList(String owner);

    void deleteTask(String owner, Long listID, Long taskID);

    void deleteAllTasks(String owner, Long listID);
}
