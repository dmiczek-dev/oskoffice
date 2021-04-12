package pl.kursant.oskoffice.service;

import java.util.List;
import pl.kursant.oskoffice.model.StudentProgress;

public interface StudentProgressService {
    
    List<StudentProgress> getStudentProgress();
    
    StudentProgress addStudentProgress(StudentProgress studentProgress);
    
    StudentProgress getStudentProgressById(Integer studentProgressId);
    
    StudentProgress updateStudentProgress(StudentProgress studentProgress);
    
    void deleteStudentProgress(Integer studentProgressId);
    
}
