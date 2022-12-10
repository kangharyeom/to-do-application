package com.todoapp.todoapplication.mapper;

import com.todoapp.todoapplication.Dto.TaskerPatchDto;
import com.todoapp.todoapplication.Dto.TaskerPostDto;
import com.todoapp.todoapplication.Dto.TaskerResponseDto;
import com.todoapp.todoapplication.entity.Tasker;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskerMapper {
    Tasker taskerPostDtoToTasker(TaskerPostDto taskerPostDto);
    Tasker taskerPatchDtoToTasker(TaskerPatchDto taskerPatchDto);

    TaskerResponseDto taskerToTaskerResponseDto(Tasker tasker);


    List<TaskerResponseDto> taskersToTaskerResponseDtos(List<Tasker> taskers);

}
