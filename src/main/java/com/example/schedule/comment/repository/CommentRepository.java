package com.example.schedule.comment.repository;

import com.example.schedule.comment.entity.Comment;
import com.example.schedule.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Schedule이 같은 List<Comment> 찾기
    List<Comment> findAllBySchedule(Schedule schedule);

    // Schedule과 Id가 같은 Comment 찾기
    Comment findByScheduleAndId(Schedule schedule, Long id);

}
