
package pl.kursant.oskoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student_progress")
public class StudentProgress {
    
    @Column(name = "theory")
    private Integer theory;
    
    @Column(name = "practice")
    private Integer practice;
    
    @Column(name = "theory_exam")
    private Integer theoryExam;
    
    @Column(name = "practice_exam")
    private Boolean practiceExam;
    
    @Id
    @Column(name = "student_id")
    private Integer studentId;

    public StudentProgress(Integer theory, Integer practice, Integer theoryExam, Boolean practiceExam, Integer studentId) {
        this.theory = theory;
        this.practice = practice;
        this.theoryExam = theoryExam;
        this.practiceExam = practiceExam;
        this.studentId = studentId;
    }
    
    public StudentProgress() {}

    public Integer getTheory() {
        return theory;
    }

    public void setTheory(Integer theory) {
        this.theory = theory;
    }

    public Integer getPractice() {
        return practice;
    }

    public void setPractice(Integer practice) {
        this.practice = practice;
    }

    public Integer getTheoryExam() {
        return theoryExam;
    }

    public void setTheoryExam(Integer theoryExam) {
        this.theoryExam = theoryExam;
    }

    public Boolean getPracticeExam() {
        return practiceExam;
    }

    public void setPracticeExam(Boolean practiceExam) {
        this.practiceExam = practiceExam;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    
}
