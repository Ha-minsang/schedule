package com.example.schedule.exception;

public class MaxCommentLimitException extends RuntimeException {

    // 댓글이 최대 개수일때 추가로 생성 시도시 던지는 예외
    public MaxCommentLimitException() {
        super("댓글은 최대 10개까지 가능합니다.");
    }
}
