package mubarak.assessment.techieplanet.solutions.applicationDevelopment.service;

import mubarak.assessment.techieplanet.solutions.applicationDevelopment.dto.StudentDto;
import mubarak.assessment.techieplanet.solutions.applicationDevelopment.model.Student;

import java.util.List;

public interface StudentService {
    Student saveStudent(StudentDto studentDto);
    List<Student> getAllStudents();
}
