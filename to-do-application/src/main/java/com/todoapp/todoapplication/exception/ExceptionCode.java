package com.todoapp.todoapplication.exception;

import lombok.Getter;

public enum ExceptionCode {
    TASKER_NOT_FOUND(404, "Member not found"),
    TASKER_EXISTS(409, "Member exists"),
    TASKER_CODE_EXISTS(409, "Coffee Code exists"),
    CANNOT_CHANGE_TASKER(403, "Order can not change"),
    NOT_IMPLEMENTATION(501, "Not Implementation"),
    INVALID_TASKER_STATUS(400, "Invalid member status");  // TO 추가된 부분

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
