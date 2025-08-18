package com.emma.Task.controllers;

import com.emma.Task.domain.dto.TaskListDto;
import com.emma.Task.domain.entities.TaskList;
import com.emma.Task.mappers.TaskListMapper;
import com.emma.Task.services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController {

    private  final TaskListService taskListService;
    private  final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

//    method to get task
    @GetMapping
    public List<TaskListDto> listTaskList(){
        return taskListService.listTaskList()
                .stream()
                .map(taskListMapper::toDto)
                .toList();

    }

//    method to create new task
    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto){
        TaskList createdTaskList = taskListService.createTaskList(
                taskListMapper.fromDto(taskListDto)
        );
        return taskListMapper.toDto(createdTaskList);
    }
}
