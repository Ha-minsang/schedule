package com.example.schedule.comment.service;

import com.example.schedule.comment.dto.*;
import com.example.schedule.comment.entity.Comment;
import com.example.schedule.comment.repository.CommentRepository;
import com.example.schedule.schedule.entity.Schedule;
import com.example.schedule.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    // CREATE 새 comment 저장
    @Transactional
    public CreateCommentResponse saveComment(Long scheduleId, CreateCommentRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("없는 일정입니다.")
        );
        List<Comment> commentList = commentRepository.findAllBySchedule(schedule);
        if (commentList.size() >= 10) {
            throw new IllegalArgumentException("댓글은 최대 10개까지만 작성 가능합니다.");
        }
        Comment comment = new Comment(
                request.getContents(),
                request.getWriter(),
                request.getPassword(),
                schedule
        );
        Comment savedComment = commentRepository.save(comment);
        return new CreateCommentResponse(
                savedComment.getId(),
                savedComment.getContents(),
                savedComment.getWriter(),
                savedComment.getCreatedAt(),
                savedComment.getModifiedAt()
        );
    }

    // UPDATE comment 수정
    @Transactional
    public UpdateCommentResponse updateComment(Long scheduleId, Long commentId, UpdateCommentRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("없는 일정입니다.")
        );
        Comment comment = commentRepository.findByScheduleAndId(schedule, commentId);
        if (comment == null) {
            throw new IllegalArgumentException("없는 댓글입니다.");
        }
        if (!(comment.getPassword().equals(request.getPassword()))) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        } else {
            comment.updateComment(
                    request.getContents(),
                    request.getWriter()
            );
            return new UpdateCommentResponse(
                    comment.getId(),
                    comment.getContents(),
                    comment.getWriter(),
                    comment.getCreatedAt(),
                    comment.getModifiedAt()
            );
        }
    }

    // DELETE comment 삭제
    public void deleteComment(Long scheduleId, Long commentId, DeleteCommentRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("없는 일정입니다.")
        );
        Comment comment = commentRepository.findByScheduleAndId(schedule, commentId);
        if (comment == null) {
            throw new IllegalArgumentException("없는 댓글입니다.");
        }
        if (request.getPassword().equals(comment.getPassword())) {
            commentRepository.delete(comment);
        } else {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
    }
}
