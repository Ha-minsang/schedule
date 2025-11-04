package com.example.schedule.comment.dto;

import lombok.Getter;

@Getter
public class CreateCommentRequest {

    private String contents;
    private String writer;
    private String password;
}
