package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TaskDto;
import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;

@Service

public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    private TaskDto mapToDto(Task task) {
        return new TaskDto(task.getId(), task.getTask(), task.getDescription());
    }

    private Task mapToEntity(TaskDto dto) {
        return new Task(dto.getId(), dto.getTask(), dto.getDescription());
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        Task task = mapToEntity(taskDto);
        return mapToDto(taskRepository.save(task));
    }

    @Override
    public TaskDto getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto taskDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTask(taskDto.getTask());
        task.setDescription(taskDto.getDescription());

        return mapToDto(taskRepository.save(task));
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
