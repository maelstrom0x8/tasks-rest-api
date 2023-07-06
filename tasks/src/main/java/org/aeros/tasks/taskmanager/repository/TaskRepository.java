package org.aeros.tasks.taskmanager.repository;

import java.util.List;
import java.util.Optional;
import org.aeros.tasks.taskmanager.domain.model.Task;
import org.aeros.tasks.taskmanager.domain.model.TaskList;

public interface TaskRepository {

    /*
     * TaskRepository should be able to SAVE A LIST, SAVE A TASK, UPDATE A TASK as well as
     * UPDATE A LIST, DELETE A LIST which will also DELETE ITS TASKS. CLEAR ALL USER LISTS
     * CLEAR ALL TASKS IN A LIST
     * can also FIND A USER LISTS BY ID and ALL LISTS FOR A USER
     * FIND TASKS IN A LIST BY ID. FIND A SINGLE TASK IN A LIST BY ID.
     * */

    TaskList save(TaskList list);

    Task save(Long listID, Task task);

    List<TaskList> findAll(String owner);

    List<Task> findAllTasks(String owner, Long listID);

    Optional<TaskList> findListById(String owner, Long id);

    Task findTaskById(String owner, Long id, Long listID);

    void deleteAll(String owner);

    void deleteList(String owner, Long listID);

    void deleteTask(String owner, Long taskID);

    void deleteAllTasks(String owner, Long listID);

    void updateTask(Long listID, Task task);

    void updateList(TaskList list);
}
