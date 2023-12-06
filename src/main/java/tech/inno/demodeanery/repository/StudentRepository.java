package tech.inno.demodeanery.repository;

import tech.inno.demodeanery.repository.dao.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
