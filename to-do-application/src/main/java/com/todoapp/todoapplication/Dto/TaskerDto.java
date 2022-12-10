package com.todoapp.todoapplication.Dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class TaskerDto {
    @NotBlank
    private long taskerId;

    @NotBlank
    private String title;

    @NotBlank
    private long orderBrother;


}
