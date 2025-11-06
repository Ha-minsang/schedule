package com.example.schedule.exception;

public class PasswordMismatchException extends RuntimeException {

    // 패스워드 잘못 입력시 던져지는 예외
    public PasswordMismatchException() {
        super("비밀번호가 틀렸습니다.");
    }
}
