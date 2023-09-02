package org.aeros.tasks.manager.service;

import org.aeros.tasks.manager.domain.dto.TaskListResponse;
import org.aeros.tasks.manager.domain.dto.TaskRequest;
import org.aeros.tasks.manager.domain.dto.TaskResponse;
import org.aeros.tasks.manager.domain.model.Task;
import org.aeros.tasks.manager.domain.model.TaskList;
import org.aeros.tasks.manager.repository.ListRepository;
import org.aeros.tasks.manager.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
class DefaultTaskManagerService implements TaskManagerService {

    @Autowired private ModelMapper mapper;

    private final TaskRepository taskRepository;
    private final ListRepository listRepository;

    DefaultTaskManagerService(TaskRepository taskRepository, ListRepository listRepository) {
        this.taskRepository = taskRepository;
        this.listRepository = listRepository;
    }

    @Override
    public TaskList createList(String title) {
        return listRepository.save(new TaskList(null, title, user()));
    }

    @Override
    public TaskResponse addTask(Long id, TaskRequest request) {
        TaskList list = listRepository.findById(user(), id).orElseThrow();
        Task task = from(request);
        task.setList(list);
        return from(taskRepository.save(task));
    }

    @Override
    public List<TaskResponse> getTasksByList(Long id) {
        return taskRepository.findAllByListId(id).stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public void updateTask(Long id, TaskRequest task) {}

    @Override
    public void deleteTask(Long listId, Long taskId) {
        Optional<TaskList> optional = listRepository.findById(user(), listId);
        optional.ifPresent(
                list -> {
                    list.getTasks().removeIf(e -> e.getId().equals(taskId));
                });
    }

    @Override
    public void deleteAllLists() {
        listRepository.deleteAll(user());
    }

    @Override
    public void deleteList(Long id) {
        listRepository.deleteById(user(), id);
    }

    @Override
    public void deleteAllTasks(Long id) {
        Optional<TaskList> optional = listRepository.findById(user(), id);
        optional.ifPresent(
                list -> {
                    list.getTasks().clear();
                });
    }

    @Override
    public List<TaskListResponse> getLists() {
        return listRepository.findAllByUser(user()).stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @Override
    public TaskListResponse getListById(Long id) {
        var list = listRepository.findById(user(), id).orElseThrow();
        return from(list);
    }

    public void updateListTitle(Long id, String title) {
        Optional<TaskList> list = listRepository.findById(user(), id);
        list.ifPresentOrElse(
                tl -> {
                    listRepository.save(new TaskList(tl.getId(), tl.getTitle(), user()));
                },
                () -> {
                    throw new IllegalArgumentException();
                });
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
