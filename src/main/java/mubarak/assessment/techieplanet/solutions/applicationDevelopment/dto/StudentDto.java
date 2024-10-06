package mubarak.assessment.techieplanet.solutions.applicationDevelopment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    private Map<String, Double> subjectScores;
}
