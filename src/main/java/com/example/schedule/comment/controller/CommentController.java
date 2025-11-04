package com.example.schedule.comment.controller;


import com.example.schedule.comment.dto.*;
import com.example.schedule.comment.service.CommentService;
import com.example.schedule.schedule.dto.DeleteScheduleRequest;
import com.example.schedule.schedule.dto.UpdateScheduleRequest;
import com.example.schedule.schedule.dto.UpdateScheduleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CreateCommentResponse> createComment(@PathVariable Long scheduleId, @RequestBody CreateCommentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.saveComment(scheduleId, request));
    }

    @PutMapping("/schedules/{scheduleId}/comments/{commentsId}")
    public ResponseEntity<UpdateCommentResponse> updateSchedule(@PathVariable Long scheduleId, Long commentId, @RequestBody UpdateCommentRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.updateComment(scheduleId, commentId, request));
    }

    @DeleteMapping("/schedules/{scheduleId}/comments/{commentsId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long scheduleId, Long commentId, @RequestBody DeleteCommentRequest request) {
        commentService.deleteComment(scheduleId, commentId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
