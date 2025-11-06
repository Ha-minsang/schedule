package com.example.schedule.exception;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException() {
        super("존재하지 않는 댓글 입니다.");
    }
}
