package org.aeros.tasks.manager.service;

import org.aeros.tasks.manager.domain.dto.TaskListResponse;
import org.aeros.tasks.manager.domain.dto.TaskRequest;
import org.aeros.tasks.manager.domain.dto.TaskResponse;
import org.aeros.tasks.manager.domain.model.Task;
import org.aeros.tasks.manager.domain.model.TaskList;
import org.aeros.tasks.manager.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
class DefaultTaskManagerService implements TaskManagerService {

    @Autowired private ModelMapper mapper;

    private final TaskRepository taskRepository;

    DefaultTaskManagerService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskList createList(String title) {
        return taskRepository.save(new TaskList(null, title, user()));
    }

    @Override
    public TaskResponse addTask(Long id, TaskRequest task) {
        TaskList list = taskRepository.findByOwner(user()).orElseThrow();
        return from(taskRepository.save(list.id(), from(task)));
    }

    @Override
    public List<TaskResponse> getTasksByList(Long id) {
        return taskRepository.findAll(id).stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public TaskResponse updateTask(Long id, TaskRequest task) {
        return null;
    }

    @Override
    public void deleteTask(Long listId, Long taskId) {
        taskRepository.deleteTask(user(), listId, taskId);
    }

    @Override
    public void deleteAllLists() {
        taskRepository.deleteAllList(user());
    }

    @Override
    public void deleteList(Long id) {
        taskRepository.deleteList(id, user());
    }

    @Override
    public void deleteAllTasks(Long id) {
        taskRepository.deleteAllTasks(user(), id);
    }

    @Override
    public List<TaskListResponse> getLists() {
        return taskRepository.findAll(user()).stream().map(this::from).collect(Collectors.toList());
    }

    @Override
    public TaskListResponse getListById(Long id) {
        return from(taskRepository.findById(id));
    }

    private TaskListResponse from(TaskList list) {
        return mapper.map(list, TaskListResponse.class);
    }

    private Task from(TaskRequest request) {
        return mapper.map(request, Task.class);
    }

    private TaskResponse from(Task task) {
        return mapper.map(task, TaskResponse.class);
    }

    private String user() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            return authentication.getName();
        }

        return null;
    }
}
