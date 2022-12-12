package com.todoapp.todoapplication.mapper;

import com.todoapp.todoapplication.Dto.TaskPatchDto;
import com.todoapp.todoapplication.Dto.TaskPostDto;
import com.todoapp.todoapplication.Dto.TaskResponseDto;
import com.todoapp.todoapplication.entity.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task taskPostDtoToTask(TaskPostDto requestBody);
    Task taskPatchDtoToTask(TaskPatchDto requestBody);

    TaskResponseDto taskToTaskResponseDto(Task task);


    List<TaskResponseDto> tasksToTaskResponseDtos(List<Task> tasks);

}
