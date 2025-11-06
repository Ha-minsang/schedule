package com.example.schedule.exception;

public class ScheduleNotFoundException extends RuntimeException {
    public ScheduleNotFoundException() {
        super("존재하지 않는 일정 입니다.");
    }
}
