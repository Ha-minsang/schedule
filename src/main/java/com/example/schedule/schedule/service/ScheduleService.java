package com.example.schedule.schedule.service;

import com.example.schedule.comment.dto.GetCommentResponse;
import com.example.schedule.comment.entity.Comment;
import com.example.schedule.comment.repository.CommentRepository;
import com.example.schedule.schedule.entity.Schedule;
import com.example.schedule.schedule.dto.*;
import com.example.schedule.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    // CREATE 새 schedule 저장
    @Transactional
    public CreateScheduleResponse saveSchedule(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContents(),
                request.getWriter(),
                request.getPassword()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContents(),
                savedSchedule.getWriter(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    // READ 작성자가 일치하는 schedule 조회 및 수정 시간 기준 내림차순 정렬
    @Transactional(readOnly = true)
    public List<GetScheduleResponse> findScheduleByWriter(String writer) {
        List<Schedule> schedules= scheduleRepository.findAllByWriterOrderByModifiedAtDesc(writer);
        List<GetScheduleResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            GetScheduleResponse dto = new GetScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContents(),
                    schedule.getWriter(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    // READ 전체 schedule 조회 및 수정 시간 기준 내림차순 정렬
    @Transactional(readOnly = true)
    public List<GetScheduleResponse> findAllSchedule() {
            List<Schedule> schedules= scheduleRepository.findAllByOrderByModifiedAtDesc();
            List<GetScheduleResponse> dtos = new ArrayList<>();
            for (Schedule schedule : schedules) {
                GetScheduleResponse dto = new GetScheduleResponse(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getContents(),
                        schedule.getWriter(),
                        schedule.getCreatedAt(),
                        schedule.getModifiedAt()
                );
                dtos.add(dto);
            }
            return dtos;
    }

    // READ 단일 schedule 조회
    @Transactional(readOnly = true)
    public GetScheduleWithCommentResponse findOneSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("없는 일정입니다.")
        );
        List<Comment> comments = commentRepository.findAllBySchedule(schedule);
        List<GetCommentResponse> commentList = new ArrayList<>();
        for (Comment comment : comments) {
            commentList.add(new GetCommentResponse(
                    comment.getId(),
                    comment.getContents(),
                    comment.getWriter(),
                    comment.getCreatedAt(),
                    comment.getModifiedAt()
            ));
        }
        return new GetScheduleWithCommentResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getWriter(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt(),
                commentList
        );
    }

    // UPDATE schedule 수정
    @Transactional
    public UpdateScheduleResponse updateSchedule(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("없는 일정입니다.")
        );
        if (!(schedule.getPassword().equals(request.getPassword()))) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        } else {
            schedule.updateSchedule(
                    request.getTitle(),
                    request.getWriter()
            );
            return new UpdateScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContents(),
                    schedule.getWriter(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
        }
    }

    // DELETE schedule 삭제
    @Transactional
    public void deleteSchedule(Long scheduleId, DeleteScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("없는 일정입니다.")
        );
        if (request.getPassword().equals(schedule.getPassword())) {
            scheduleRepository.delete(schedule);
        } else {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
    }
}
