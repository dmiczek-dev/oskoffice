package pl.kursant.oskoffice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.kursant.oskoffice.model.StudentDetailsView;

@Transactional
public interface StudentDetailsViewRepository extends JpaRepository<StudentDetailsView, Integer> {
    
    @Query(value ="SELECT login, first_name, last_name\n" +
        "FROM users INNER JOIN student USING(user_id)\n", nativeQuery = true)
    public List<StudentDetailsView> getAllStudentsWithDetails();
    
}
