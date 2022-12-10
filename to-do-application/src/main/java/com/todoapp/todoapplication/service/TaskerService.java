package com.todoapp.todoapplication.service;

import com.todoapp.todoapplication.entity.Tasker;
import com.todoapp.todoapplication.exception.BusinessLogicException;
import com.todoapp.todoapplication.exception.ExceptionCode;
import com.todoapp.todoapplication.repository.TaskerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskerService {
    private TaskerRepository taskerRepository;

    public TaskerService(TaskerRepository taskerRepository) {
        this.taskerRepository = taskerRepository;
    }

    public Tasker createTasker(Tasker tasker) {
        verifiedExistsTitle(tasker.getTitle());
        return taskerRepository.save(tasker);
    }


    public Tasker updateTasker(Tasker tasker) {
        Tasker findTasker = findVerifiedTasker(tasker.getTaskerId());
        Optional.ofNullable(tasker.getTitle())
                .ifPresent(title -> findTasker.setTitle(title));
        Optional.ofNullable(tasker.getOrderBrother())
                .ifPresent(orderBrother -> findTasker.setOrderBrother(orderBrother));

        return taskerRepository.save(findTasker);
    }

    public Page<Tasker> findTaskers(int page, int size) {
        return taskerRepository.findAll(PageRequest.of(page, size, Sort.by("taskId").descending()));
    }

    public void deleteTasker(long taskerId) {
        Tasker findTasker = findVerifiedTasker(taskerId);

        taskerRepository.delete(findTasker);
    }



    private Tasker findVerifiedTasker(long taskerId) {
        Optional<Tasker> optionalTasker = taskerRepository.findById(taskerId);
        Tasker findTasker =
                optionalTasker.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.TASKER_CODE_EXISTS));
        return findTasker;
    }

    private void verifiedExistsTitle(String title) {
        Optional<Tasker> tasker = taskerRepository.findByTitle(title);
        if (tasker.isPresent())
            throw new BusinessLogicException(ExceptionCode.TASKER_EXISTS);
    }

    public Tasker findTasker(long taskerId) {
        return findVerifiedTasker(taskerId);
    }
}