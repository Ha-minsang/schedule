package com.example.schedule.comment.entity;

import com.example.schedule.schedule.entity.BaseEntity;
import com.example.schedule.schedule.entity.Schedule;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

    @Getter
    @Entity
    @Table(name = "comments")
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class Comment extends BaseEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(length = 200, nullable = false)
        private String contents;
        @Column(nullable = false)
        private String writer;
        @Column(nullable = false)
        private String password;
        @ManyToOne
        @JoinColumn(name = "schedules_id")
        private Schedule schedule;

        public Comment(String contents, String writer, String password) {
            this.contents = contents;
            this.writer = writer;
            this.password = password;
        }

        public void updateComment(String title, String writer) {
            this.contents = contents;
            this.writer = writer;
        }
}
