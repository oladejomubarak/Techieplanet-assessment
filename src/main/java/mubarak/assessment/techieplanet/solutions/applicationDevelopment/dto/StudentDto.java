package mubarak.assessment.techieplanet.solutions.applicationDevelopment.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Map;
@Data
public class StudentDto {
    @NotBlank(message = "First name is mandatory")
    @Size(max = 50, message = "First name can't exceed 50 characters")
    private String firstName;
    @NotBlank(message = "Last name is mandatory")
    @Size(max = 50, message = "Last name can't exceed 50 characters")
    private String lastName;
    @NotNull(message = "Subject scores are mandatory")
    @Size(min = 5, max = 5, message = "There must be exactly 5 subjects")
    private Map<@NotBlank(message = "Subject name cannot be blank") String,
            @NotNull(message = "Score is mandatory")
            @Min(value = 0, message = "Score must be at least 0")
            @Max(value = 100, message = "Score cannot exceed 100") Double> subjectScores;

}
