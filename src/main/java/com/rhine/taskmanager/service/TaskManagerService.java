package com.rhine.taskmanager.service;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.rhine.taskmanager.domain.dto.TaskListResponse;
import com.rhine.taskmanager.domain.dto.TaskRequest;
import com.rhine.taskmanager.domain.dto.TaskResponse;
import com.rhine.taskmanager.domain.model.Task;
import com.rhine.taskmanager.domain.model.TaskList;
import com.rhine.taskmanager.repository.TaskListRepository;
import com.rhine.taskmanager.repository.TaskRepository;

@Service
public class TaskManagerService {

    @Autowired
    private ModelMapper mapper;

    private TaskListRepository taskListRepository;
    private TaskRepository taskRepository;

    public TaskManagerService(TaskListRepository taskListRepository, TaskRepository taskRepository) {
        this.taskListRepository = taskListRepository;
        this.taskRepository = taskRepository;
    }

    public TaskListResponse createList(String name) {
        String user = authenticatedUser();

        TaskList list = new TaskList(name, user);
        TaskList saved = taskListRepository.save(list);

        return fromTaskList(saved);
    }

    public void deleteList(Long id) {
        taskListRepository.deleteById(id);
    }

    public List<TaskListResponse> getTaskLists() {
        String user = authenticatedUser();

        return taskListRepository.findAllByUser(user)
                .stream()
                .map(this::fromTaskList).collect(toList());
    }

    public List<TaskResponse> getTasksByList(Long id) {
        String user = authenticatedUser();
        Optional<TaskList> list = taskListRepository.findByUser(user, id);
        if (list.isPresent()) {
            return list.get().getTasks().stream()
                    .map(this::fromTask)
                    .collect(toList());
        }
        return Collections.emptyList();
    }

    public Optional<TaskResponse> createTaskForUser(Long listId, TaskRequest taskRequest) {
        String user = authenticatedUser();
        Optional<TaskList> list = taskListRepository.findByUser(user, listId);
        if (list.isPresent()) {
            Task task = toTask(taskRequest);
            var s = taskRepository.save(task);
            list.get().getTasks().add(task);
            return Optional.ofNullable(fromTask(s));
        }
        return Optional.empty();
    }

    public void deleteListById(Long id) {
        String user = authenticatedUser();
        taskListRepository.deleteUserListById(user, id);
    }

    public void deleteAllLists() {
        String user = authenticatedUser();
        taskListRepository.deleteAllUserList(user);
    }

    public void deleteTasksFromList(Long listId, Long taskId) {
        String user = authenticatedUser();
        Optional<TaskList> list = taskListRepository.findByUser(user, listId);
        if (list.isPresent()) {
            list.get().getTasks().removeIf(e -> e.getId().equals(taskId));
            taskListRepository.save(list.get());
        }
    }

    public void clearAllTasksFromList(Long listId) {
        String user = authenticatedUser();
        Optional<TaskList> list = taskListRepository.findByUser(user, listId);
        if (list.isPresent()) {
            list.get().getTasks().clear();
            taskListRepository.save(list.get());
        }
    }

    public TaskResponse updateTask(Long taskId, TaskRequest taskRequest) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent()) {
            var t = task.get();
            t.setName(taskRequest.name());
            t.setDescription(taskRequest.description());
            t.setSchedule(taskRequest.schedule());
            t.setCompleted(taskRequest.completed());

            return fromTask(taskRepository.save(t));
        }

        return null;

    }

    private TaskListResponse fromTaskList(TaskList list) {
        TaskListResponse listDto = mapper.map(list, TaskListResponse.class);
        return listDto;
    }

    private TaskResponse fromTask(Task task) {
        TaskResponse taskResponse = mapper.map(task, TaskResponse.class);
        return taskResponse;
    }

    private Task toTask(TaskRequest taskRequest) {
        Task task = mapper.map(taskRequest, Task.class);
        return task;
    }

    private String authenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

}
