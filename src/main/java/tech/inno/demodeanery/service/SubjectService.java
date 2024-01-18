package tech.inno.demodeanery.service;

import lombok.NonNull;
import tech.inno.demodeanery.controller.dto.CreateSubjectRequest;
import tech.inno.demodeanery.controller.dto.SubjectResponse;
import tech.inno.demodeanery.repository.dao.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> findAll();

    SubjectResponse findById(@NonNull Long subjectId);

    @NonNull
    SubjectResponse createSubject(@NonNull CreateSubjectRequest request);

    @NonNull
    SubjectResponse update(@NonNull CreateSubjectRequest request);

    void delete(@NonNull Long id);

    void deleteByIdIn(List<Long> ids);

}
