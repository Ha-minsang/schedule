package com.example.schedule.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DeleteCommentRequest {

    @NotBlank
    private String password;
}
