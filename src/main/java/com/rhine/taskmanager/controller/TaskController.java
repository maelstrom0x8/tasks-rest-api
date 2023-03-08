package com.rhine.taskmanager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rhine.taskmanager.domain.dto.TaskListDto;
import com.rhine.taskmanager.service.TaskManagerService;

@RestController
@RequestMapping("/api")
public class TaskController {

    private TaskManagerService taskService;

    public TaskController(TaskManagerService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/list/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskListDto createNewList(@RequestParam String title) {
        return taskService.createList(title);
    }

    @GetMapping("/list")
    public List<TaskListDto> getLists() {
        return taskService.getTaskLists();
    }
}
