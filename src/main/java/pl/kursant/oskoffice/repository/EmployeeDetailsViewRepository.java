package pl.kursant.oskoffice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.kursant.oskoffice.model.EmployeeDetailsView;

@Transactional
public interface EmployeeDetailsViewRepository extends JpaRepository<EmployeeDetailsView, Integer> {
    
    @Query(value ="SELECT * FROM employee_details", nativeQuery = true)
    public List<EmployeeDetailsView> getAllEmployeesWithDetails();
    
}
