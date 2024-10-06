package mubarak.assessment.techieplanet.solutions.applicationDevelopment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mubarak.assessment.techieplanet.solutions.applicationDevelopment.dto.StudentDto;
import mubarak.assessment.techieplanet.solutions.applicationDevelopment.dto.StudentReportDto;
import mubarak.assessment.techieplanet.solutions.applicationDevelopment.model.Score;
import mubarak.assessment.techieplanet.solutions.applicationDevelopment.model.Student;
import mubarak.assessment.techieplanet.solutions.applicationDevelopment.model.Subject;
import mubarak.assessment.techieplanet.solutions.applicationDevelopment.repository.ScoreRepository;
import mubarak.assessment.techieplanet.solutions.applicationDevelopment.repository.StudentRepository;
import mubarak.assessment.techieplanet.solutions.applicationDevelopment.repository.SubjectRepository;
import mubarak.assessment.techieplanet.solutions.utils.StudentNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService{
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final ScoreServiceImpl scoreService;
    private final StudentRepository studentRepository;
    private final ScoreRepository scoreRepository;
    private final SubjectRepository subjectRepository;
    public Student saveStudent(StudentDto studentDto) {
        logger.debug("Saving student: {}", studentDto);

        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());

        List<Score> scores = studentDto.getSubjectScores().entrySet().stream().map(entry -> {
            Subject subject = subjectRepository.findByName(entry.getKey())
                    .orElseGet(() -> subjectRepository.save(new Subject(entry.getKey())));

            Score score = new Score();
            score.setStudent(student);
            score.setSubject(subject);
            score.setValue(entry.getValue());
            scoreRepository.save(score);
            return score;
        }).collect(Collectors.toList());

        student.setScores(scores);
        Student savedStudent = studentRepository.save(student);

        logger.info("Student saved with ID: {}", savedStudent.getId());
        return savedStudent;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public StudentReportDto getStudentReport(Long studentId) {
        logger.info("Generating report for student ID: {}", studentId);
        Student student = getStudentById(studentId);
        List<Double> scores = student.getScores().stream()
                .map(Score::getValue)
                .collect(Collectors.toList());

        Double mean = scoreService.calculateMean(scores);
        Double median = scoreService.calculateMedian(scores);
        List<Double> mode = scoreService.calculateMode(scores);

        StudentReportDto report = new StudentReportDto();
        report.setFirstName(student.getFirstName());
        report.setLastName(student.getLastName());
        report.setMeanScore(mean);
        report.setMedianScore(median);
        report.setModeScores(mode);

        Map<String, Double> subjectScores = student.getScores().stream()
                .collect(Collectors.toMap(s -> s.getSubject().getName(), Score::getValue));
        report.setSubjectScores(subjectScores);

        logger.info("Report generated for student ID: {}", studentId);
        return report;
    }

    private Student getStudentById(Long id){
        return studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException(id));
    }
}
