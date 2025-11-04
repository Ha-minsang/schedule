package com.example.schedule.schedule.dto;

import com.example.schedule.comment.dto.GetCommentResponse;
import com.example.schedule.comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetScheduleWithCommentResponse {

    private final Long id;
    private final String title;
    private final String contents;
    private final String writer;
    private final List<GetCommentResponse> commentList;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetScheduleWithCommentResponse(Long id, String title, String contents, String writer, List<GetCommentResponse> commentList, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.commentList = commentList;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
