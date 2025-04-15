package com.hrbackend.hrbackend.service;

import com.hrbackend.hrbackend.dto.LeaveRequestDTO;
import com.hrbackend.hrbackend.entity.DatabaseSequence;
import com.hrbackend.hrbackend.entity.LeaveRequest; // Changed import
import com.hrbackend.hrbackend.repository.LeaveRequestRepository;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class LeaveRequestService {
    private final LeaveRequestRepository leaveRequestRepository;
    private final MongoOperations mongoOperations;

    public LeaveRequestService(LeaveRequestRepository leaveRequestRepository,
                               MongoOperations mongoOperations) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.mongoOperations = mongoOperations;
    }

    // Changed return type and object creation
    public LeaveRequest createLeaveRequest(LeaveRequestDTO dto) {
        LeaveRequest request = new LeaveRequest(); // Use renamed class

        String customId = generateNextId();
        request.setId(customId);

        request.setEmployeeName(dto.getEmployeeName());
        request.setLeaveType(dto.getLeaveType());
        request.setStartDate(dto.getStartDate());
        request.setEndDate(dto.getEndDate());
        request.setReason(dto.getReason());

        request.setStatus("PENDING");
        request.setCreatedAt(LocalDate.now());

        return leaveRequestRepository.save(request);
    }

    private String generateNextId() {
        DatabaseSequence counter = mongoOperations.findAndModify(
                query(Criteria.where("_id").is(LeaveRequest.SEQUENCE_NAME)), // Use renamed class
                new Update().inc("seq", 1),
                options().returnNew(true).upsert(true),
                DatabaseSequence.class
        );
        return String.format("%04d", Objects.requireNonNullElse(
                counter != null ? counter.getSeq() : null, 1111));
    }

    // Updated return types
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }

    public LeaveRequest updateLeaveRequestStatus(String id, String status) {
        return leaveRequestRepository.findById(id)
                .map(request -> {
                    request.setStatus(status);
                    return leaveRequestRepository.save(request);
                })
                .orElseThrow(() -> new RuntimeException("Leave request not found: " + id));
    }

    public List<LeaveRequest> getLeaveRequestsByEmployee(String employeeName) {
        return leaveRequestRepository.findByEmployeeName(employeeName);
    }

    public List<LeaveRequest> getPendingLeaveRequests() {
        return leaveRequestRepository.findByStatus("PENDING");
    }

    public void deleteLeaveRequest(String id) {
        leaveRequestRepository.deleteById(id);
    }
}