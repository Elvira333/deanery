package com.example.demo.controller;

import com.example.demo.pojo.CreateStudentRequest;
import com.example.demo.pojo.StudentResponse;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class StudentController {
    private StudentService studentService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<StudentResponse> findAll() {
        return studentService.findAll();
    }

    @GetMapping(value = "/{userId}", produces = APPLICATION_JSON_VALUE)
    public StudentResponse findById(@PathVariable Long studentId) {
        return studentService.findById(studentId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public StudentResponse create(@RequestBody CreateStudentRequest request) {
        return studentService.createUser(request);
    }

    @PatchMapping(value = "/{userId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public StudentResponse update(@PathVariable Long studentId, @RequestBody CreateStudentRequest request) {
        return studentService.update(studentId, request);
    }
}
