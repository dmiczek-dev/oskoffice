
package pl.kursant.oskoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "practice_term")
public class PracticeTerm {
    
    @Id
    @Column(name = "term_id")
    private Integer termId;
    
    @Column(name = "vehicle_id")
    private Integer vehicleId;
    
    @Column(name = "student_id")
    private Integer studentId;

    public PracticeTerm(Integer termId, Integer vehicleId, Integer studentId) {
        this.termId = termId;
        this.vehicleId = vehicleId;
        this.studentId = studentId;
    }
    
    public PracticeTerm() {}

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
    
    
    
}
