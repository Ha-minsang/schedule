package com.example.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateScheduleResponse {

    private final Long id;
    private final String title;
    private final String memo;
    private final String writer;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CreateScheduleResponse(Long id, String title, String memo, String writer, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.memo = memo;
        this.writer = writer;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
