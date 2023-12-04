package com.example.demo.service;

import com.example.demo.pojo.CreateStudentRequest;
import com.example.demo.pojo.StudentResponse;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

public interface StudentService {

    @NotNull
    List<StudentResponse> findAll();

    @NotNull
    StudentResponse findById(@NotNull Long studentId);

    @NotNull
    StudentResponse createUser(@NotNull CreateStudentRequest request);

    @NotNull
    StudentResponse update(@NotNull Long studentId, @NotNull CreateStudentRequest request);

    void delete(@NotNull Long studentId);
}
