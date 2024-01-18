package tech.inno.demodeanery.repository.dao;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import tech.inno.demodeanery.controller.dto.StudentResponse;
import tech.inno.demodeanery.controller.dto.SubjectResponse;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "student_subject",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return name.equals(subject.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void setStudents(Set<StudentResponse> studentResponses) {
        Set<Student> students = new HashSet<>();

        for (StudentResponse studentResponse : studentResponses) {
            Student student = new Student();
            student.setLogin(studentResponse.getLogin());
            student.setName(studentResponse.getName());
            student.setSurname(studentResponse.getSurname());
            student.setAge(studentResponse.getAge());

            students.add(student);
        }

        this.students = students;

    }
}
