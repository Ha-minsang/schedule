package com.example.schedule.exception;

public class ScheduleNotFoundException extends RuntimeException {

    // 일정이 존재하지 않을시 던지는 예외
    public ScheduleNotFoundException() {
        super("존재하지 않는 일정 입니다.");
    }
}
