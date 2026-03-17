package com.homevidya.app.homevidya.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutorSubmitRequest {
    
    @NotEmpty(message = "At least one subject must be selected")
    private List<Long> subjectIds;
}
