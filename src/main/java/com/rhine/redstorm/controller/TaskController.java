package com.rhine.redstorm.controller;

import java.util.List;
import static java.util.stream.Collectors.toList;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rhine.redstorm.dto.TaskInDto;
import com.rhine.redstorm.dto.TaskListDto;
import com.rhine.redstorm.dto.TaskOutDto;
import com.rhine.redstorm.model.Task;
import com.rhine.redstorm.model.TaskList;
import com.rhine.redstorm.service.TaskService;

@RestController
@RequestMapping("/api/v1/rhine")
public class TaskController {

    private final Logger LOG = LoggerFactory.getLogger(TaskController.class);

    private TaskService taskService;
    private ModelMapper modelMapper;

    public TaskController(TaskService taskService, ModelMapper modelMapper) {
        this.taskService = taskService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/list/create")
    public TaskListDto createNewList(@RequestParam String name) {
        LOG.info("Creating new list: " + name);
        return convertTaskListToDto(taskService.createList(name));
    }

    @PostMapping("/task/create")
    public TaskOutDto createNewTask(@RequestParam Long list_id, @RequestBody TaskInDto taskDto) {
        LOG.info("Adding task \'" + taskDto.getName() + "\' to [list:id=" + list_id + "]");
        Task task = taskDtoToTask(taskDto);
        taskService.addTask(list_id, task);
        return taskToTaskOutDto(task);
    }

    @GetMapping("/list")
    public List<TaskListDto> getLists() {
        return taskService.getTaskLists().stream()
                .map(t -> convertTaskListToDto(t))
                .collect(toList());
    }

    @GetMapping("/task")
    public List<TaskOutDto> getListTasks(@RequestParam Long list_id) {
        return taskService.getTasksByList(list_id).stream()
                .map(t -> taskToTaskOutDto(t))
                .collect(toList());
    }

    @DeleteMapping("/list/{id}")
    public void deleteList(@PathVariable Long list_id) {
        taskService.deleteList(list_id);
    }

    @DeleteMapping("/list")
    public void clearAllLists() {
        taskService.deleteAllLists();
    }

    private TaskListDto convertTaskListToDto(TaskList list) {
        TaskListDto listDto = modelMapper.map(list, TaskListDto.class);
        return listDto;
    }

    private TaskOutDto taskToTaskOutDto(Task task) {
        return modelMapper.map(task, TaskOutDto.class);
    }

    private Task taskDtoToTask(TaskInDto taskDto) {
        return modelMapper.map(taskDto, Task.class);
    }

}
