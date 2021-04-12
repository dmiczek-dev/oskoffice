package pl.kursant.oskoffice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kursant.oskoffice.model.Student;
import pl.kursant.oskoffice.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    StudentRepository studentRepo;
    
    @Override
    public List<Student> getStudent() {
        return studentRepo.findAll();
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepo.saveAndFlush(student);
    }

    @Override
    public Student getStudentById(Integer studentId) {
        return studentRepo.findOne(studentId);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepo.saveAndFlush(student);
    }

    @Override
    public void deleteStudent(Integer studentId) {
        studentRepo.delete(studentId);
    }
    
}
