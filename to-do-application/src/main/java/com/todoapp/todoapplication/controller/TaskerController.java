package com.todoapp.todoapplication.controller;

import com.todoapp.todoapplication.Dto.TaskerPatchDto;
import com.todoapp.todoapplication.Dto.TaskerPostDto;
import com.todoapp.todoapplication.entity.Tasker;
import com.todoapp.todoapplication.mapper.TaskerMapper;
import com.todoapp.todoapplication.response.MultiResponseDto;
import com.todoapp.todoapplication.response.SingleResponseDto;
import com.todoapp.todoapplication.service.TaskerService;
import com.todoapp.todoapplication.utils.UriCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/taskers")
@Validated
@Slf4j
public class TaskerController {
    private final static String TASKER_DEFAULT_URL = "/taskers";
    private final TaskerService taskerService;
    private final TaskerMapper mapper;

    public TaskerController(TaskerService taskerService, TaskerMapper mapper) {
        this.taskerService = taskerService;
        this.mapper = mapper;
    }



    @PostMapping
    public ResponseEntity postTasker(@Valid @RequestBody TaskerPostDto requestBody){
        Tasker tasker = mapper.taskerPostDtoToTasker(requestBody);

        Tasker createdTasker = taskerService.createTasker(tasker);
        URI location = UriCreator.createUri(TASKER_DEFAULT_URL, createdTasker.getTaskerId());


        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{tasker-id}")
    public ResponseEntity patchTasker(
            @PathVariable("tasker-id") @Positive long taskerId,
            @Valid @RequestBody TaskerPatchDto requestBody) {
        requestBody.setTaskerId(taskerId);

        Tasker tasker =
                taskerService.updateTasker(mapper.taskerPatchDtoToTasker(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.taskerToTaskerResponseDto(tasker)),
                HttpStatus.OK);
    }

    @GetMapping("/{tasker-id}")
    public ResponseEntity getTasker(
            @PathVariable("tasker-id") @Positive long taskerId) {
        Tasker tasker = taskerService.findTasker(taskerId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.taskerToTaskerResponseDto(tasker))
                , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTasks(@Positive @RequestParam int page,
                                   @Positive @RequestParam int size) {
        Page<Tasker> pageTaskers = taskerService.findTaskers(page - 1, size);
        List<Tasker> taskers = pageTaskers.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.taskersToTaskerResponseDtos(taskers),
                        pageTaskers),
                HttpStatus.OK);
    }

    @DeleteMapping("/{task-id}")
    public ResponseEntity deleteTasker(
            @PathVariable("task-id") @Positive long taskerId) {
        taskerService.deleteTasker(taskerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}