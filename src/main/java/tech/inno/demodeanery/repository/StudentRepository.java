package tech.inno.demodeanery.repository;

import org.springframework.stereotype.Repository;
import tech.inno.demodeanery.repository.dao.Student;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
