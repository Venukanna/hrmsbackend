package com.hrbackend.hrbackend.repository;

import com.hrbackend.hrbackend.entity.LeaveRequest; // Changed from MongoLeaveRequest
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface LeaveRequestRepository extends MongoRepository<LeaveRequest, String> { // Changed to LeaveRequest
    List<LeaveRequest> findByEmployeeName(String employeeName); // Changed return type
    List<LeaveRequest> findByStatus(String status); // Changed return type
    List<LeaveRequest> findByLeaveTypeAndStatus(String leaveType, String status); // Changed return type

    Optional<LeaveRequest> findTopByOrderByIdDesc(); // Changed return type ✅
}