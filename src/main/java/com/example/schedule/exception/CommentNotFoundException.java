package com.example.schedule.exception;

public class CommentNotFoundException extends RuntimeException {

    // 댓글이 존재하지 않을시 던지는 예외
    public CommentNotFoundException() {
        super("존재하지 않는 댓글 입니다.");
    }
}
