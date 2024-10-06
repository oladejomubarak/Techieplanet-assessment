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
public class StudentServiceUnitTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ScoreRepository scoreRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private ScoreServiceImpl scoreService;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createStudent_success() {
        // Given
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName("John");
        studentDto.setLastName("Doe");
        Map<String, Double> subjectScores = new HashMap<>();
        subjectScores.put("Mathematics", 85.0);
        subjectScores.put("English", 90.0);
        subjectScores.put("Science", 78.0);
        subjectScores.put("History", 88.0);
        subjectScores.put("Art", 92.0);
        studentDto.setSubjectScores(subjectScores);

        // Create a Student instance
        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());

        // Mock the behavior of saving the student
        when(studentRepository.save(any(Student.class))).thenAnswer(invocation -> {
            Student savedStudent = invocation.getArgument(0);
            savedStudent.setId(1L);
            return savedStudent;
        });

        when(subjectRepository.findByName(anyString())).thenReturn(Optional.empty());
        when(subjectRepository.save(any(Subject.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Mock saving scores
        when(scoreRepository.save(any(Score.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        ResponseObject<Object> response = studentService.createStudent(studentDto);

        // Then
        assertEquals(HttpStatus.OK.value(), response.getCode());
        assertTrue(response.isStatus());
        assertEquals(1L, response.getData());
        verify(studentRepository, times(1)).save(any(Student.class));
        verify(scoreRepository, times(5)).save(any(Score.class));
    }


    @Test
    public void createStudent_failure_invalidSubjectCount() {
        // Given
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName("John");
        studentDto.setLastName("Doe");
        Map<String, Double> subjectScores = new HashMap<>();
        subjectScores.put("Mathematics", 85.0);
        studentDto.setSubjectScores(subjectScores);

        // When / Then
        assertThrows(BadRequestException.class, () -> studentService.createStudent(studentDto));
    }

    @Test
    public void getStudentReport_success() {
        // Given
        Long studentId = 1L;
        Student student = new Student();
        student.setId(studentId);
        student.setFirstName("John");
        student.setLastName("Doe");
        List<Score> scores = Arrays.asList(
                new Score(1L, 85.0, student, new Subject("Mathematics")),
                new Score(2L, 90.0, student, new Subject("English")),
                new Score(3L, 78.0, student, new Subject("Science")),
                new Score(4L, 88.0, student, new Subject("History")),
                new Score(5L, 92.0, student, new Subject("Art"))
        );
        student.setScores(scores);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(scoreService.calculateMean(anyList())).thenReturn(86.6);
        when(scoreService.calculateMedian(anyList())).thenReturn(88.0);
        when(scoreService.calculateMode(anyList())).thenReturn(Collections.singletonList(92.0));

        // When
        ResponseObject<Object> response = studentService.getStudentReport(studentId);

        // Then
        assertEquals(HttpStatus.OK.value(), response.getCode());
        assertTrue(response.isStatus());
        assertTrue(response.getData() instanceof StudentReportDto);
        StudentReportDto report = (StudentReportDto) response.getData();
        assertEquals("John", report.getFirstName());
        assertEquals("Doe", report.getLastName());
        assertEquals(86.6, report.getMeanScore());
        assertEquals(88.0, report.getMedianScore());
        assertEquals(Collections.singletonList(92.0), report.getModeScores());
        verify(studentRepository, times(1)).findById(studentId);
    }

    @Test
    public void getStudentReport_failure_studentNotFound() {
        // Given
        Long studentId = 1L;

        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(StudentNotFoundException.class, () -> studentService.getStudentReport(studentId));
    }
}
