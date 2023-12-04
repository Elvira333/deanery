package com.example.demo.service;

import com.example.demo.pojo.CreateStudentRequest;
import com.example.demo.pojo.CreateSubjectRequest;
import com.example.demo.pojo.StudentResponse;
import com.example.demo.pojo.SubjectResponse;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.dao.Student;
import com.example.demo.repository.dao.Subject;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService{
    private final StudentRepository studentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponse> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(this::buildStudentResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public StudentResponse findById(Long studentId) {
        return studentRepository.findById(studentId)
                .map(this::buildStudentResponse)
                .orElseThrow(() -> new EntityNotFoundException("User " + studentId + " is not found"));
    }

    @Override
    @Transactional
    public StudentResponse createUser(CreateStudentRequest request) {
        Student student = buildStudentRequest(request);
        return buildStudentResponse(studentRepository.save(student));
    }

    @Override
    @Transactional
    public StudentResponse update(Long studentId, CreateStudentRequest request) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("User " + studentId + " is not found"));
        studentUpdate(student, request);
        return buildStudentResponse(studentRepository.save(student));
    }

    @Override
    @Transactional
    public void delete(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @NonNull
    private StudentResponse buildStudentResponse( Student student) {
        return new StudentResponse()
                .setId(student.getId())
                .setLogin(student.getLogin())
                .setAge(student.getAge())
                .setFirstName(student.getFirstName())
                .setMiddleName(student.getMiddleName())
                .setLastName(student.getLastName())
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
    private Student buildStudentRequest( CreateStudentRequest request) {
        Set<Subject> subjects = new HashSet<>();
        if (request.getSubject() != null) {
            subjects.add(new Subject().setName(request.getSubject().getName()));
        }

        return new Student()
                .setLogin(request.getLogin())
                .setAge(request.getAge())
                .setFirstName(request.getFirstName())
                .setMiddleName(request.getMiddleName())
                .setLastName(request.getLastName())
                .setSubjects(subjects);
    }

    @NonNull
    private void studentUpdate(Student student, CreateStudentRequest request) {
        ofNullable(request.getLogin()).map(student::setLogin);
        ofNullable(request.getFirstName()).map(student::setFirstName);
        ofNullable(request.getMiddleName()).map(student::setMiddleName);
        ofNullable(request.getLastName()).map(student::setLastName);
        ofNullable(request.getAge()).map(student::setAge);

        CreateSubjectRequest subjectRequest = request.getSubject();
        if (subjectRequest != null) {
            Set<Subject> subjects = new HashSet<>();
            subjects.add(new Subject().setName(subjectRequest.getName()));
            student.setSubjects(subjects);
        }
    }
}