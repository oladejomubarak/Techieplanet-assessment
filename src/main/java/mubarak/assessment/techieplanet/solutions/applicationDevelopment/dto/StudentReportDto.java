package mubarak.assessment.techieplanet.solutions.applicationDevelopment.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class StudentReportDto {
    private String firstName;
    private String lastName;
    private Map<String, Double> subjectScores;
    private Double meanScore;
    private Double medianScore;
    private List<Double> modeScores;
}
