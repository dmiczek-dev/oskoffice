package pl.kursant.oskoffice.service;

import java.util.List;
import pl.kursant.oskoffice.model.Student;

public interface StudentService {
    
    List<Student> getStudent();
    
    Student addStudent(Student student);
    
    Student getStudentById(Integer studentId);
    
    Student updateStudent(Student student);
    
    void deleteStudent(Integer studentId);
    
}
