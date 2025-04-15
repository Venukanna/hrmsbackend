package com.hrbackend.hrbackend.controller;

import com.hrbackend.hrbackend.dto.LeaveRequestDTO;
import com.hrbackend.hrbackend.entity.LeaveRequest; // Changed import
import com.hrbackend.hrbackend.service.LeaveRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave-requests")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LeaveRequest createLeaveRequest(@RequestBody LeaveRequestDTO dto) { // Updated return type
        return leaveRequestService.createLeaveRequest(dto);
    }

    @GetMapping
    public List<LeaveRequest> getAllLeaveRequests() { // Updated return type
        return leaveRequestService.getAllLeaveRequests();
    }

    @GetMapping("/employee/{employeeName}")
    public List<LeaveRequest> getLeaveRequestsByEmployee(@PathVariable String employeeName) { // Updated
        return leaveRequestService.getLeaveRequestsByEmployee(employeeName);
    }

    @GetMapping("/pending")
    public List<LeaveRequest> getPendingLeaveRequests() { // Updated
        return leaveRequestService.getPendingLeaveRequests();
    }

    @PatchMapping("/{id}/status")
    public LeaveRequest updateLeaveRequestStatus( // Updated return type
                                                  @PathVariable String id,
                                                  @RequestParam String status) {
        return leaveRequestService.updateLeaveRequestStatus(id, status);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLeaveRequest(@PathVariable String id) {
        leaveRequestService.deleteLeaveRequest(id);
    }
}