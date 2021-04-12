package pl.kursant.oskoffice.model;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "termview")
@Entity(name = "termview")
public class TermView {
    
    @Id
    @Column(name = "term_id")
    private Integer termId;
    @Column(name = "employee_id")
    private Integer employeeId;
    @Column(name = "firm_id")
    private Integer firmId;
    @Column(name = "date")
    private Date date;
    @Column(name = "start_time")
    private Time startTime;
    @Column(name = "end_time")
    private Time endTime;
    @Column(name = "description")
    private String description;
    @Column(name = "student_id", nullable = true)
    private Integer studentId;
    @Column(name = "vehicle_id", nullable = true)
    private Integer vehicleId;

    public TermView(Integer termId, Integer employeeId, Integer firmId, Date date, Time startTime, Time endTime, String description, Integer studentId, Integer vehicleId) {
        this.termId = termId;
        this.employeeId = employeeId;
        this.firmId = firmId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.studentId = studentId;
        this.vehicleId = vehicleId;
    }
    
    public TermView() {}

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getFirmId() {
        return firmId;
    }

    public void setFirmId(Integer firmId) {
        this.firmId = firmId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }
    
    
    
}
