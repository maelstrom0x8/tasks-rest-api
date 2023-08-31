package org.aeros.tasks.manager.repository;

import org.aeros.tasks.manager.domain.model.Task;

import java.util.List;

public interface TaskRepository {

    Task save(Long listID, Task task);

    List<Task> findAll(Long listId);

    void deleteAll(String owner, Long listId);

    void deleteSingle(String owner, Long listID, Long taskID);

    void update(Long listId, Task task);
}
