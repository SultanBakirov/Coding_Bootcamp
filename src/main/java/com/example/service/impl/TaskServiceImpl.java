package com.example.service.impl;

import com.example.models.Task;
import com.example.repository.TaskRepository;
import com.example.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public List<Task> getAllTasks(Long id) {
        return taskRepository.getAllTasks(id);
    }

    @Override
    public void addTask(Long id, Task task) {
        taskRepository.addTask(id, task);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.getTaskById(id);
    }

    @Override
    public void updateTask(Task task, Long id) {
        taskRepository.updateTask(task, id);
    }

    @Override
    public void deleteTaskById(Long id) {
        taskRepository.deleteTaskById(id);
    }
}
