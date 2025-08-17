package com.emma.Task.mappers.impl;

import com.emma.Task.domain.dto.TaskListDto;
import com.emma.Task.domain.entities.Task;
import com.emma.Task.domain.entities.TaskList;
import com.emma.Task.domain.entities.TaskStatus;
import com.emma.Task.mappers.TaskListMapper;
import com.emma.Task.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {

    private final  TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                Optional.ofNullable(taskListDto.task())
                        .map(task -> task.stream()
                                .map(taskMapper::fromDto)
                                .toList() ).orElse(null),
                null,
                null
        );
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(taskList.getTask())
                        .map(List::size)
                        .orElse(0),
                calculateTaskListProgress(taskList.getTask()),
                Optional.ofNullable(taskList.getTask())
                        .map(task ->
                                task.stream().map(taskMapper::toDto).toList()
                        ).orElse(null)

        );
    }

//    calculating progress
    private Double calculateTaskListProgress(List<Task> tasks){
        if(null ==tasks){
            return null;
        }
        long closedTaskCount=  tasks.stream().filter(task ->
                TaskStatus.CLOSED == task.getStatus() ).count();

        return  (double) closedTaskCount/tasks.size();
    }
}
