package com.example.schedule.schedule.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {

    private String title;
    private String contents;
    private String writer;
    private String password;
}
