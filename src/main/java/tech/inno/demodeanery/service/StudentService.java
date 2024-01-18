package tech.inno.demodeanery.service;

import lombok.NonNull;
import tech.inno.demodeanery.controller.dto.CreateStudentRequest;
import tech.inno.demodeanery.controller.dto.StudentResponse;


import java.util.List;

public interface StudentService {

    @NonNull
    List<StudentResponse> findAll();

    StudentResponse findById(@NonNull Long studentId);

    @NonNull
    StudentResponse createStudent(@NonNull CreateStudentRequest request);

    @NonNull
    StudentResponse update(@NonNull Long studentId, @NonNull CreateStudentRequest request);

    void delete(@NonNull Long studentId);
}
