package com.homevidya.app.homevidya.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutorPricingRequest {
    
    @NotNull(message = "Subject ID is required")
    private Long subjectId;
    
    @NotNull(message = "Class ID is required")
    private Long classId;
    
    @NotNull(message = "Fee per hour is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Fee must be greater than 0")
    @DecimalMax(value = "10000.0", message = "Fee seems unrealistic")
    private Double feePerHour;
    
    @NotNull(message = "Monthly fee is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Fee must be greater than 0")
    @DecimalMax(value = "100000.0", message = "Fee seems unrealistic")
    private Double monthlyFee;
}
