package tech.inno.demodeanery.controller.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import tech.inno.demodeanery.repository.dao.Subject;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

@Data
@Accessors(chain = true)
public class SubjectResponse {
    private String name;

    private List<StudentResponse> students;

    public void setStudents(Set<StudentResponse> studentResponses) {
        this.students = List.copyOf(studentResponses);
    }
}
