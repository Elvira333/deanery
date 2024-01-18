package tech.inno.demodeanery.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tech.inno.demodeanery.controller.dto.CreateStudentRequest;
import tech.inno.demodeanery.controller.dto.CreateSubjectRequest;
import tech.inno.demodeanery.controller.dto.StudentResponse;
import tech.inno.demodeanery.controller.dto.SubjectResponse;
import tech.inno.demodeanery.repository.StudentRepository;
import tech.inno.demodeanery.repository.dao.Student;
import tech.inno.demodeanery.repository.dao.Subject;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.inno.demodeanery.service.StudentService;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    @Transactional(readOnly = true)
    public @NonNull List<StudentResponse> findAll() {
        log.info("Список всех студентов");
        return studentRepository.findAll()
                .stream()
                .map(this::buildStudentResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public StudentResponse findById(@NonNull Long studentId) {
        log.info("Данные студента под ID: " + studentId);
        return studentRepository.findById(studentId)
                .map(this::buildStudentResponse)
                .orElseThrow(() -> new EntityNotFoundException("Cтудент  " + studentId + " не найден"));
    }

    @Override
    @Transactional
    public @NonNull StudentResponse createStudent(@NonNull CreateStudentRequest request) {
        log.info("Студент успешно создан");
        Student student = buildStudentRequest(request);
        return buildStudentResponse(studentRepository.save(student));
    }

    @Override
    @Transactional
    public @NonNull StudentResponse update(@NonNull Long studentId, @NonNull CreateStudentRequest request) {
        log.info("Данные студента обновлены под ID: " + studentId);
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Студент " + studentId + " не найден"));
        studentUpdate(student, request);
        return buildStudentResponse(studentRepository.save(student));
    }

    @Override
    @Transactional
    public void delete(@NonNull Long studentId) {
        log.info("Студент удален под id: " + studentId);
        studentRepository.deleteById(studentId);
    }

    @NonNull
    private StudentResponse buildStudentResponse(Student student) {
        return new StudentResponse()
                .setLogin(student.getLogin())
                .setAge(student.getAge())
                .setName(student.getName())
                .setSurname(student.getSurname())
                .setSubjectResponses(buildSubjectResponses(student.getSubjects()));
    }

    private Set<SubjectResponse> buildSubjectResponses(Set<Subject> subjects) {
        if (subjects == null || subjects.isEmpty()) {
            return Collections.emptySet();
        }

        Set<SubjectResponse> subjectResponses = new HashSet<>();
        for (Subject subject : subjects) {
            SubjectResponse subjectResponse = new SubjectResponse()
                    .setName(subject.getName());
            subjectResponses.add(subjectResponse);
        }
        return subjectResponses;
    }
    @NonNull
    private Student buildStudentRequest(CreateStudentRequest request) {
        Set<Subject> subjects = new HashSet<>();
        if (request.getSubject() != null) {
            subjects.add(new Subject().setName(request.getSubject().getName()));
        }

        return new Student()
                .setLogin(request.getLogin())
                .setAge(request.getAge())
                .setName(request.getName())
                .setSurname(request.getSurname())
                .setSubjects(subjects);
    }

    private void studentUpdate(Student student, CreateStudentRequest request) {
        ofNullable(request.getLogin()).map(student::setLogin);
        ofNullable(request.getName()).map(student::setName);
        ofNullable(request.getSurname()).map(student::setSurname);
        ofNullable(request.getAge()).map(student::setAge);

        CreateSubjectRequest subjectRequest = request.getSubject();
        if (subjectRequest != null) {
            Set<Subject> subjects = new HashSet<>();
            subjects.add(new Subject().setName(subjectRequest.getName()));
            student.setSubjects(subjects);
        }
    }
}