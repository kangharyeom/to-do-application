package com.todoapp.todoapplication.service;

import com.todoapp.todoapplication.entity.Task;
import com.todoapp.todoapplication.exception.BusinessLogicException;
import com.todoapp.todoapplication.exception.ExceptionCode;
import com.todoapp.todoapplication.repository.TaskRepository;
import com.todoapp.todoapplication.utils.CustomBeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository, CustomBeanUtils<Task> beanUtils) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        verifiedExistsTitle(task.getTitle());
        return taskRepository.save(task);
    }


    public Task updateTask(Task task) {
        Task findTask = findVerifiedTask(task.getTaskId());
        Optional.ofNullable(task.getTitle())
                .ifPresent(findTask::setTitle);
        Optional.of(task.getTodo_order())
                .ifPresent(findTask::setTodo_order);
        Optional.of(task.isCompleted())
                .ifPresent(findTask::setCompleted);

        return taskRepository.save(findTask);
    }

    public Task findTask(long taskId) {
        return findVerifiedTask(taskId);
    }

    public Page<Task> findTasks(int page, int size) {
        return taskRepository.findAll(PageRequest.of(page, size, Sort.by("taskId").descending()));
    }

    public void deleteTask(long taskId) {
        Task findTask = findVerifiedTask(taskId);

        taskRepository.delete(findTask);
    }



    private Task findVerifiedTask(long taskId) {
        Optional<Task> optionalTask =
                taskRepository.findById(taskId);
        Task findTask =
                optionalTask.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.TASK_NOT_FOUND));
        return findTask;
    }

    private void verifiedExistsTitle(String title) {
        Optional<Task> task = taskRepository.findByTitle(title);
        if (task.isPresent())
            throw new BusinessLogicException(ExceptionCode.TASK_EXISTS);
    }
}