package pl.kursant.oskoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.kursant.oskoffice.model.StudentProgress;


@Transactional
public interface StudentProgressRepository extends JpaRepository<StudentProgress, Integer>{
    
    
}
