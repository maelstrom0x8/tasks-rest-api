package org.aeros.tasks.manager.repository;

import org.aeros.tasks.manager.domain.model.TaskList;

import java.util.List;
import java.util.Optional;

public interface ListRepository {

    TaskList save(TaskList list);

    Optional<TaskList> findById(String user, Long id);

    List<TaskList> findAll(String user);

    void deleteAll(String user);

    void deleteById(String user, Long id);

    void update(String user, TaskList list);
}
