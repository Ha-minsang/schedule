package com.example.schedule.schedule.repository;

import com.example.schedule.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByWriterOrderByModifiedAtDesc(String writer);
    List<Schedule> findAllByOrderByModifiedAtDesc();
}
