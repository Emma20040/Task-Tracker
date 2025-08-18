package com.emma.Task.services;

import  java.util.List;
import  com.emma.Task.domain.entities.TaskList;
public interface TaskListService {
    List<TaskList> listTaskList();
    TaskList createTaskList(TaskList taskList);
}
