package com.homevidya.app.homevidya.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutorEducationRequest {
    
    @NotBlank(message = "Highest qualification is required")
    private String highestQualification;
    
    @NotBlank(message = "Degree name is required")
    private String degreeName;
    
    @NotBlank(message = "University/Institute name is required")
    private String university;
    
    @NotNull(message = "Passing year is required")
    @Min(value = 1950, message = "Invalid year")
    @Max(value = 2099, message = "Invalid year")
    private Integer passingYear;
    
    @NotBlank(message = "Certificate URL is required")
    private String certificateUrl;
}
