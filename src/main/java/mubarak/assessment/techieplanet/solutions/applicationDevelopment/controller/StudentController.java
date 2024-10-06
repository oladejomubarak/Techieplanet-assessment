package mubarak.assessment.techieplanet.solutions.applicationDevelopment.controller;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mubarak.assessment.techieplanet.solutions.applicationDevelopment.dto.ResponseObject;
import mubarak.assessment.techieplanet.solutions.applicationDevelopment.dto.StudentDto;
import mubarak.assessment.techieplanet.solutions.applicationDevelopment.service.StudentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("core/techieplanet/students/")
@RequiredArgsConstructor
@Tag(name = "Student Controller", description = "APIs for managing students")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final StudentServiceImpl studentService;

    @Operation(summary = "Create a new student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("create")
    public ResponseEntity<?> createStudent(
            @Valid @RequestBody StudentDto studentDto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.warn("Validation errors while creating a student: {}", bindingResult.getFieldErrors());
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResponseEntity.badRequest().body(errors);
        }

        ResponseObject<Object> savedStudent = studentService.createStudent(studentDto);
        return ResponseEntity.ok(savedStudent);
    }

    @Operation(summary = "Get student report")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Report retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @GetMapping("report")
    public ResponseEntity<Object> getStudentReport(@RequestParam(name = "id") Long id) {
        ResponseObject<Object> report = studentService.getStudentReport(id);
        return ResponseEntity.ok(report);
    }
}
