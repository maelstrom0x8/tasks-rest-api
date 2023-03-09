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

import com.rhine.taskmanager.domain.dto.TaskListDto;
import com.rhine.taskmanager.domain.model.Task;
import com.rhine.taskmanager.domain.model.TaskList;
import com.rhine.taskmanager.repository.TaskListRepository;

@Service
public class TaskManagerService {

    @Autowired
    private ModelMapper mapper;

    private TaskListRepository taskListRepository;

    public TaskManagerService(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    public TaskListDto createList(String name) {
        String user = authenticatedUser();

        TaskList list = new TaskList(name, user);
        TaskList saved = taskListRepository.save(list);

        return fromTaskList(saved);
    }

    public void deleteList(Long id) {
        taskListRepository.deleteById(id);
    }

    public List<TaskListDto> getTaskLists() {
        String user = authenticatedUser();

        return taskListRepository.findAllByUser(user)
                .stream()
                .map(this::fromTaskList).collect(toList());
    }

    public List<Task> getTasksByList(Long id) {
        String user = authenticatedUser();
        Optional<TaskList> list = taskListRepository.findByUser(user, id);
        if (list.isPresent()) {
            return list.get().getTasks();
        }
        return Collections.emptyList();
    }

    public void deleteListById(Long id) {
        String user = authenticatedUser();
        taskListRepository.deleteUserListById(user, id);
    }

    public void deleteAllLists() {
        String user = authenticatedUser();
        taskListRepository.deleteAllUserList(user);
    }

    private TaskListDto fromTaskList(TaskList list) {
        TaskListDto listDto = mapper.map(list, TaskListDto.class);
        return listDto;
    }

    private String authenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

}
