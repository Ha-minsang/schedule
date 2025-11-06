package com.example.schedule.schedule.dto;

import com.example.schedule.comment.dto.GetCommentResponse;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetScheduleWithCommentResponse {

    private final Long id;
    private final String title;
    private final String contents;
    private final String writer;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final List<GetCommentResponse> commentList;


    public GetScheduleWithCommentResponse(Long id, String title, String contents, String writer, LocalDateTime createdAt, LocalDateTime modifiedAt, List<GetCommentResponse> commentList) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.commentList = commentList;
    }
}
