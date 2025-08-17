package com.emma.Task.mappers;
import com.emma.Task.domain.dto.TaskDto;
import com.emma.Task.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);
    TaskDto toDto(Task task);
}
