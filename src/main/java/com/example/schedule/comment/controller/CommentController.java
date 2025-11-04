package com.example.schedule.comment.controller;


import com.example.schedule.comment.dto.*;
import com.example.schedule.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // CREATE 새로운 comment 생성
    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CreateCommentResponse> createComment(@PathVariable Long scheduleId, @RequestBody CreateCommentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.saveComment(scheduleId, request));
    }

    // UPDATE comment 수정
    @PutMapping("/schedules/{scheduleId}/comments/{commentsId}")
    public ResponseEntity<UpdateCommentResponse> updateSchedule(@PathVariable Long scheduleId, Long commentId, @RequestBody UpdateCommentRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.updateComment(scheduleId, commentId, request));
    }

    // DELETE comment 삭제
    @DeleteMapping("/schedules/{scheduleId}/comments/{commentsId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long scheduleId, Long commentId, @RequestBody DeleteCommentRequest request) {
        commentService.deleteComment(scheduleId, commentId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
