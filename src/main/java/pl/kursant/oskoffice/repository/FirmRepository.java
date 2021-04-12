package pl.kursant.oskoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.kursant.oskoffice.model.Firm;


@Transactional
public interface FirmRepository extends JpaRepository<Firm, Integer>{
    
    
}
