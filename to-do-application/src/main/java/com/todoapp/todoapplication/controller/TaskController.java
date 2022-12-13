package com.todoapp.todoapplication.controller;

import com.todoapp.todoapplication.Dto.TaskPatchDto;
import com.todoapp.todoapplication.Dto.TaskPostDto;
import com.todoapp.todoapplication.entity.Task;
import com.todoapp.todoapplication.mapper.TaskMapper;
import com.todoapp.todoapplication.response.MultiResponseDto;
import com.todoapp.todoapplication.response.SingleResponseDto;
import com.todoapp.todoapplication.service.TaskService;
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
@RequestMapping("/")
@Validated
@CrossOrigin("https://todobackend.com")
@Slf4j
public class TaskController {
    private final static String TASK_DEFAULT_URL = "/";
    private final TaskService taskService;
    private final TaskMapper mapper;

    public TaskController(TaskService taskService, TaskMapper mapper) {
        this.taskService = taskService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postTask(@Valid @RequestBody TaskPostDto requestBody){
        Task task = mapper.taskPostDtoToTask(requestBody);

        Task createdTask = taskService.createTask(task);
        URI location = UriCreator.createUri(TASK_DEFAULT_URL, createdTask.getTaskId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchTask(
            @PathVariable("id") @Positive long taskId,
            @Valid @RequestBody TaskPatchDto requestBody) {
        requestBody.setTaskId(taskId);

        Task task =
                taskService.updateTask(mapper.taskPatchDtoToTask(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.taskToTaskResponseDto(task)),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getTask(
            @PathVariable("id") @Positive long taskId) {
        Task task = taskService.findTask(taskId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(mapper.taskToTaskResponseDto(task))
                , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTasks(@Positive @RequestParam int page,
                                   @Positive @RequestParam int size) {
        Page<Task> pageTasks = taskService.findTasks(page - 1, size);
        List<Task> tasks = pageTasks.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.tasksToTaskResponseDtos(tasks),
                        pageTasks),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(
            @PathVariable("id") @Positive long taskId) {
        taskService.deleteTask(taskId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}