package com.internship.tool.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class RegulatoryChangeRequest {
    private String title;
    private String description;
    private String source;
    private String status;
    private LocalDate effectiveDate;
}
