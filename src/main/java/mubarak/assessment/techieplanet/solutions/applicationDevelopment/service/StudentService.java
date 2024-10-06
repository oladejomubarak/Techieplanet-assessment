package mubarak.assessment.techieplanet.solutions.applicationDevelopment.service;

import mubarak.assessment.techieplanet.solutions.applicationDevelopment.dto.ResponseObject;
import mubarak.assessment.techieplanet.solutions.applicationDevelopment.dto.StudentDto;

public interface StudentService {
    ResponseObject<Object> createStudent(StudentDto studentDto);
    ResponseObject<Object> getStudentReport(Long studentId);
    ResponseObject<Object> getAllStudents();
}
