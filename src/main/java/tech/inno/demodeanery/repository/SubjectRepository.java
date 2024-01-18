package tech.inno.demodeanery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.inno.demodeanery.repository.dao.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
}
