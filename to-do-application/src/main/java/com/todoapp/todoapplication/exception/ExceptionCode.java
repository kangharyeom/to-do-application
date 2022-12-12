package com.todoapp.todoapplication.exception;

import lombok.Getter;

public enum ExceptionCode {
    TASK_NOT_FOUND(404, "Member not found"),
    TASK_EXISTS(409, "Task exists"),
    CANNOT_CHANGE_TASK(403, "Order can not change"),
    NOT_IMPLEMENTATION(501, "Not Implementation"),
    INVALID_TASK_STATUS(400, "Invalid member status");  // TO 추가된 부분

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
