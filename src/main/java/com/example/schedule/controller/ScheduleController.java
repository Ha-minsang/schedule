package com.example.schedule.controller;

import com.example.schedule.dto.CreateScheduleRequest;
import com.example.schedule.dto.CreateScheduleResponse;
import com.example.schedule.dto.GetScheduleResponse;
import com.example.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getSchedulesByWriter(@RequestParam String writer) {
        if (writer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findByWriter(writer));
        }
    }
}
