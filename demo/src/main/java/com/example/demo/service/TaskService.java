package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.TaskDto;

public interface TaskService {
    TaskDto createTask(TaskDto taskDto);
    TaskDto getTaskById(Long id);
    List<TaskDto> getAllTasks();
    TaskDto updateTask(Long id, TaskDto taskDto);
    void deleteTask(Long id);
}

