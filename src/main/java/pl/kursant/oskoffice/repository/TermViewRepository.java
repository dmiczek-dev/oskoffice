package pl.kursant.oskoffice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.kursant.oskoffice.model.TermView;

@Transactional
public interface TermViewRepository extends JpaRepository<TermView, Integer> {
    
    @Query(value ="select * from termview where date >= current_date - 7;" , nativeQuery = true)
    List<TermView> getTermsByCurrentDate();
    
}
