package com.example.schedule.comment.entity;

import com.example.schedule.schedule.entity.BaseEntity;
import com.example.schedule.schedule.entity.Schedule;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

    @Getter
    @Entity
    @Table(name = "comments")
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class Comment extends BaseEntity {

        // PK
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "comments_id")
        private Long id;

        // 댓글 내용
        @Column(length = 200, nullable = false)
        private String contents;

        // 댓글 작성자
        @Column(nullable = false)
        private String writer;

        // 비밀번호
        @Column(nullable = false)
        private String password;

        // 다대일 관계 FK
        @ManyToOne
        @JoinColumn(name = "schedules_id", nullable=false)
        private Schedule schedule;

        // 생성자
        public Comment(String contents, String writer, String password, Schedule schedule) {
            this.contents = contents;
            this.writer = writer;
            this.password = password;
            this.schedule = schedule;
        }

        // 세터
        public void updateComment(String contents, String writer) {
            this.contents = contents;
            this.writer = writer;
        }
}
