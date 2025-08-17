package com.emma.Task.domain.dto;

import java.util.UUID;
import  java.util.List;

public record TaskListDto(
        UUID id,
        String title,
        String description,
        Integer count,
        double progress,
        List<TaskDto> task
) {
}
