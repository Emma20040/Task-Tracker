package com.emma.Task.services.impl;

import com.emma.Task.domain.entities.TaskList;
import com.emma.Task.repositories.TaskListRepository;
import com.emma.Task.services.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

//    method to get all tasklist
    @Override
    public List<TaskList> listTaskList() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {
        if (null != taskList.getId()){
            throw new IllegalArgumentException("Task list already has an id");

        }
        if (null == taskList.getTitle() || taskList.getTitle().isBlank()){
            throw new IllegalArgumentException("Task List must have a title");
        }

        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));

    }

//    get tasklist by id
    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepository.findById(id);
    }


//    update task list
    @Override
    public TaskList updateTaskList(UUID taskListId, TaskList taskList) {
        if (null== taskList.getId()){
            throw new IllegalArgumentException("Task list must have an id");
        }

        if (!Objects.equals(taskList.getId(), taskListId)){
            throw  new IllegalArgumentException("Trying to change th ID, which is not permitted");
        }

        TaskList existingTaskList = taskListRepository.findById(taskListId).orElseThrow(() ->
                new IllegalArgumentException("Task with that id does not exist"));

        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdated(LocalDateTime.now());
        return  taskListRepository.save(existingTaskList);
    }

    @Override
    public void deleteTaskList(UUID taskListId) {
        taskListRepository.deleteById(taskListId);
    }
}
