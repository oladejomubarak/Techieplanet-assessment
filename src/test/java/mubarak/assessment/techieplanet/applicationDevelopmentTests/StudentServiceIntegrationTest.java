package mubarak.assessment.techieplanet.applicationDevelopmentTests;

import mubarak.assessment.techieplanet.solutions.applicationDevelopment.dto.ResponseObject;
import mubarak.assessment.techieplanet.solutions.applicationDevelopment.dto.StudentDto;
import mubarak.assessment.techieplanet.solutions.applicationDevelopment.dto.StudentReportDto;
import mubarak.assessment.techieplanet.solutions.applicationDevelopment.model.Score;
import mubarak.assessment.techieplanet.solutions.applicationDevelopment.model.Student;
import mubarak.assessment.techieplanet.solutions.applicationDevelopment.model.Subject;
import mubarak.assessment.techieplanet.solutions.applicationDevelopment.repository.ScoreRepository;
import mubarak.assessment.techieplanet.solutions.applicationDevelopment.repository.StudentRepository;
import mubarak.assessment.techieplanet.solutions.applicationDevelopment.repository.SubjectRepository;
import mubarak.assessment.techieplanet.solutions.applicationDevelopment.service.ScoreServiceImpl;
import mubarak.assessment.techieplanet.solutions.applicationDevelopment.service.StudentServiceImpl;
import mubarak.assessment.techieplanet.solutions.utils.BadRequestException;
import mubarak.assessment.techieplanet.solutions.utils.StudentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentServiceIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createStudent_integrationTest() {
        // Given
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName("Jane");
        studentDto.setLastName("Doe");
        Map<String, Double> subjectScores = new HashMap<>();
        subjectScores.put("Mathematics", 85.0);
        subjectScores.put("English", 90.0);
        subjectScores.put("Science", 78.0);
        subjectScores.put("History", 88.0);
        subjectScores.put("Art", 92.0);
        studentDto.setSubjectScores(subjectScores);

        // When
        ResponseEntity<ResponseObject> response = restTemplate.postForEntity("/core/techieplanet/students/create", studentDto, ResponseObject.class);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isStatus());
        assertEquals("SUCCESSFUL", response.getBody().getMessage());
    }

    @Test
    public void getStudentReport_integrationTest() {
        // Given
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName("Jane");
        studentDto.setLastName("Doe");
        Map<String, Double> subjectScores = new HashMap<>();
        subjectScores.put("Mathematics", 85.0);
        subjectScores.put("English", 90.0);
        subjectScores.put("Science", 78.0);
        subjectScores.put("History", 88.0);
        subjectScores.put("Art", 92.0);
        studentDto.setSubjectScores(subjectScores);

        // Send POST request to create student
        ResponseEntity<ResponseObject> createResponse = restTemplate.postForEntity("/core/techieplanet/students/create", studentDto, ResponseObject.class);
        assertNotNull(createResponse.getBody());

        // Get the student ID and handle the possible type difference
        Object data = createResponse.getBody().getData();
        Long studentId;

        if (data instanceof Integer) {
            studentId = ((Integer) data).longValue();  // Convert Integer to Long if needed
        } else if (data instanceof Long) {
            studentId = (Long) data;  // Cast directly if already a Long
        } else {
            throw new IllegalStateException("Unexpected ID type: " + data.getClass().getName());
        }

        // When - Send GET request to get student report
        ResponseEntity<ResponseObject> response = restTemplate.exchange("/core/techieplanet/students/report?id={id}", HttpMethod.GET, null, ResponseObject.class, studentId);

        // Then - Validate response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isStatus());
        assertEquals("SUCCESSFUL", response.getBody().getMessage());
        assertTrue(response.getBody().getData() instanceof Map);
    }

}
