package tech.inno.demodeanery.controller;

import org.springframework.http.HttpStatus;
import tech.inno.demodeanery.controller.dto.CreateStudentRequest;
import tech.inno.demodeanery.controller.dto.StudentResponse;
import tech.inno.demodeanery.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<StudentResponse> findAll() {
        return studentService.findAll();
    }

    @GetMapping(value = "/{studentId}", produces = APPLICATION_JSON_VALUE)
    public StudentResponse findById(@PathVariable Long studentId) {
        return studentService.findById(studentId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public StudentResponse createStudent(@RequestBody CreateStudentRequest request) {
        return studentService.createStudent(request);
    }

    @PatchMapping(value = "/{studentId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public StudentResponse update(@PathVariable Long studentId, @RequestBody CreateStudentRequest request) {
        return studentService.update(studentId, request);
    }
}
