package pl.kursant.oskoffice.repository;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.kursant.oskoffice.model.Employee;


@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    
    @Query(value = "SELECT * FROM employee WHERE user_id = ?1", nativeQuery = true)
    Employee getEmployeeByUserId(Integer userId);
}

