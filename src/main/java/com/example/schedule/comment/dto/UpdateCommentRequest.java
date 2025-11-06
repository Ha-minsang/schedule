package com.example.schedule.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateCommentRequest {

    @NotBlank
    private String contents;
    @NotBlank
    private String writer;
    @NotBlank
    private String password;
}
