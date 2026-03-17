package com.homevidya.app.homevidya.dto;

import com.homevidya.app.homevidya.enums.WorkType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutorExperienceRequest {
    
    @NotNull(message = "Total experience is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Experience must be greater than 0")
    @DecimalMax(value = "70.0", message = "Experience cannot exceed 70 years")
    private Double totalExperience;
    
    @NotNull(message = "Work type is required")
    private WorkType workType;
    
    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 1000, message = "Description should be between 10 and 1000 characters")
    private String description;
}
