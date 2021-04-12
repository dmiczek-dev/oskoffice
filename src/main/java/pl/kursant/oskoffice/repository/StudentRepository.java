package pl.kursant.oskoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.kursant.oskoffice.model.Student;
import pl.kursant.oskoffice.model.User;


@Transactional
public interface StudentRepository extends JpaRepository<Student, Integer>{
    
    @Query(value = "SELECT * FROM student WHERE user_id = ?1", nativeQuery = true)
    Student getStudentByUserId(Integer userId);
    
}
