package com.example.schedule.schedule.controller;

import com.example.schedule.schedule.dto.*;
import com.example.schedule.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // CREATE 새 schedule 저장
    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(@Valid @RequestBody CreateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.saveSchedule(request));
    }

    // READ 작성자가 일치하는 schedule 조회
    // 작성자 미입력시 전체 schedule 조회
    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getSchedulesByWriter(@RequestParam(required = false) String writer) {
        if (writer == null) {
            return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAllSchedule());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findScheduleByWriter(writer));
        }
    }

    // READ schedule 단일 조회
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<GetScheduleWithCommentResponse> getSchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findOneSchedule(scheduleId));
    }

    // UPDATE schedule 수정
    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(@PathVariable Long scheduleId, @Valid @RequestBody UpdateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.updateSchedule(scheduleId, request));
    }

    // DELETE schedule 수정
    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId, @Valid @RequestBody DeleteScheduleRequest request) {
        scheduleService.deleteSchedule(scheduleId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
