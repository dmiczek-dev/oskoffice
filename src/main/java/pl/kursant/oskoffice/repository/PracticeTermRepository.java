package pl.kursant.oskoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.kursant.oskoffice.model.PracticeTerm;


@Transactional
public interface PracticeTermRepository extends JpaRepository<PracticeTerm, Integer>{
    
    
}
