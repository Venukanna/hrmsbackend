package com.hrbackend.hrbackend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "counters")
public class DatabaseSequence {
    @Id
    private String id;
    private long seq; // Changed from int to long

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public long getSeq() { return seq; } // Updated return type
    public void setSeq(long seq) { this.seq = seq; } // Updated parameter type
}