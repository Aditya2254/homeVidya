package com.homevidya.app.homevidya.dto;

import com.homevidya.app.homevidya.enums.DocumentType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutorDocumentRequest {
    
    @NotNull(message = "Document type is required")
    private DocumentType documentType;
    
    @NotBlank(message = "Document URL is required")
    private String documentUrl;
    
    @NotBlank(message = "Document number is required")
    private String documentNumber;
}
