package com.emma.Task.controllers;

import com.emma.Task.domain.dto.TaskListDto;
import com.emma.Task.domain.entities.Task;
import com.emma.Task.domain.entities.TaskList;
import com.emma.Task.mappers.TaskListMapper;
import com.emma.Task.services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

//    get taskList by id
    @GetMapping(path="/{task_list_id}")
    public Optional<TaskListDto> getTaskList(@PathVariable("task_list_id") UUID taskListId){
        return taskListService.getTaskList(taskListId).map(taskListMapper::toDto);
    }

//    update taskList by id
    @PutMapping(path="/{task_list_id}")
    public TaskListDto updateTaskList(
            @PathVariable("task_list_id") UUID taskListId,
            @RequestBody TaskListDto taskListDto
    ){
        TaskList updatedTasklist = taskListService.updateTaskList(
                taskListId,
                taskListMapper.fromDto(taskListDto)
        );
        return  taskListMapper.toDto(updatedTasklist);
    }

//    delete taskList
    @DeleteMapping(path= "/{task_list_id}")
    public void deleteTaskList(@PathVariable("task_list_id") UUID taskListId){
        taskListService.deleteTaskList(taskListId);
    }

}
