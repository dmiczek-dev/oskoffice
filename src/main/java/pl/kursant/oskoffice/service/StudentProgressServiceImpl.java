package pl.kursant.oskoffice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kursant.oskoffice.model.StudentProgress;
import pl.kursant.oskoffice.repository.StudentProgressRepository;

@Service
public class StudentProgressServiceImpl implements StudentProgressService {
    
    @Autowired
    StudentProgressRepository studentProgressRepo;

    @Override
    public List<StudentProgress> getStudentProgress() {
        return studentProgressRepo.findAll();
    }

    @Override
    public StudentProgress addStudentProgress(StudentProgress studentProgress) {
        return studentProgressRepo.saveAndFlush(studentProgress);
    }

    @Override
    public StudentProgress getStudentProgressById(Integer studentProgressId) {
        return studentProgressRepo.findOne(studentProgressId);
    }

    @Override
    public StudentProgress updateStudentProgress(StudentProgress studentProgress) {
        return studentProgressRepo.saveAndFlush(studentProgress);
    }

    @Override
    public void deleteStudentProgress(Integer studentProgressId) {
        studentProgressRepo.delete(studentProgressId);
    }
    
}
