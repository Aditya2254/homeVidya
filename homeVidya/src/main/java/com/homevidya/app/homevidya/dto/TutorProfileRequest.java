package com.homevidya.app.homevidya.dto;

import com.homevidya.app.homevidya.enums.Gender;
import com.homevidya.app.homevidya.enums.TeachingMode;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutorProfileRequest {
    
    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number")
    private String mobileNumber;
    
    @NotNull(message = "Gender is required")
    private Gender gender;
    
    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;
    
    @NotBlank(message = "City is required")
    private String city;
    
    @NotBlank(message = "Area is required")
    private String area;
    
    @NotBlank(message = "Full address is required")
    private String fullAddress;
    
    @NotBlank(message = "Pin code is required")
    @Pattern(regexp = "^[0-9]{6}$", message = "Invalid pin code")
    private String pinCode;
    
    @Size(max = 500, message = "Bio should not exceed 500 characters")
    private String bio;
    
    @NotNull(message = "Teaching mode is required")
    private TeachingMode teachingMode;
    
    private String profilePhotoUrl;
}
