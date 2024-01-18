package tech.inno.demodeanery.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.inno.demodeanery.controller.dto.CreateSubjectRequest;
import tech.inno.demodeanery.controller.dto.SubjectResponse;
import tech.inno.demodeanery.repository.dao.Subject;
import tech.inno.demodeanery.service.SubjectService;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/subject")
@AllArgsConstructor
public class SubjectController {
    private SubjectService subjectService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Subject> findAll() {
        return subjectService.findAll();
    }

    @GetMapping(value = "/{subjectId}", produces = APPLICATION_JSON_VALUE)
    public SubjectResponse findById(@PathVariable Long subjectId) {
        return subjectService.findById(subjectId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public SubjectResponse createSubject(@RequestBody CreateSubjectRequest request) {
        return subjectService.createSubject(request);
    }

    @PatchMapping(value = "/{subjectId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public SubjectResponse update(@PathVariable Long subjectId, @RequestBody CreateSubjectRequest request) {
        return subjectService.update(request);
    }
}
