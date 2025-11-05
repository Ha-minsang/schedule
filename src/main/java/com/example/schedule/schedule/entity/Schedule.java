package com.example.schedule.schedule.entity;

import com.example.schedule.comment.entity.Comment;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedules_id")
    private Long id;

    // schedule 제목
    @Column(length = 30, nullable = false)
    private String title;

    // schedule 내용
    @Column(length = 200, nullable = false)
    private String contents;

    // schedule 작성자
    @Column(nullable = false)
    private String writer;

    // 비밀번호
    @Column(nullable = false)
    private String password;

    // 일대다 관계
    // schedule 삭제시 해당 schedule_Id에 포함된 comment도 같이 삭제
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    List<Comment> comments =  new ArrayList<>();

    // 생성자
    public  Schedule(String title, String contents, String writer, String password) {
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.password = password;
    }

    // 세터
    public void updateSchedule(String title, String writer) {
        this.title = title;
        this.writer = writer;
    }
}
