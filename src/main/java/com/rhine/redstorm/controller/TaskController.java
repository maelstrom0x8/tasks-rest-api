package com.rhine.redstorm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rhine.redstorm.domain.TaskList;
import com.rhine.redstorm.service.TaskService;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/create/{name}")
    TaskList createNewList(@PathVariable String name) {
        return taskService.createList(name);
    }
    
}
