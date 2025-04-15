package com.hrbackend.hrbackend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Document(collection = "leave_requests") // MongoDB collection name
@Getter
@Setter
public class LeaveRequest { // ✅ Renamed class to match error requirements
    public static final String SEQUENCE_NAME = "leaveRequestId";

    @Id
    private String id; // MongoDB ID field
    private String employeeName;
    private String leaveType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String reason;
    private String status = "PENDING";
    private LocalDate createdAt = LocalDate.now();
}