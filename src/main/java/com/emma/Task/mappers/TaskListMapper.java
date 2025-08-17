package com.emma.Task.mappers;
import com.emma.Task.domain.entities.TaskList;
import  com.emma.Task.domain.dto.TaskListDto;
public interface TaskListMapper {
    TaskList fromDto(TaskListDto taskListDto);
    TaskListDto toDto(TaskList taskList);
}
