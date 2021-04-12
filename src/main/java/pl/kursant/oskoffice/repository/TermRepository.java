package pl.kursant.oskoffice.repository;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.kursant.oskoffice.model.Term;
import pl.kursant.oskoffice.model.TermView;


@Transactional
public interface TermRepository extends JpaRepository<Term, Integer>{
    
    @Query(value ="select date from generate_series(current_date, (current_date + interval '1 MONTH'), '1 day') date;" , nativeQuery = true)
    List<Timestamp> getMonthTerms();
    
    
}
