package com.example.schedule.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateScheduleRequest {

    @NotBlank
    private String title;
    @NotBlank
    private String writer;
    @NotBlank
    private String password;
}
