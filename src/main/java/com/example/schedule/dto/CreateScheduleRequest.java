package com.example.schedule.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {

    private String title;
    private String memo;
    private String writer;
    private String password;
}
