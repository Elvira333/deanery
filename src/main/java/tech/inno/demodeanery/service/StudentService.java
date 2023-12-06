package tech.inno.demodeanery.service;

import tech.inno.demodeanery.controller.dto.CreateStudentRequest;
import tech.inno.demodeanery.controller.dto.StudentResponse;
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
