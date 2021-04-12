
package pl.kursant.oskoffice.model;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "term")
public class Term {
    
    @Id
    @SequenceGenerator(sequenceName="term_term_id_seq", name="term_term_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "term_term_id_seq")
    @Column(name = "term_id")
    private Integer termId;
    
    @Column(name = "date")
    private Date date;
    
    @Column(name = "start_time")
    private Time startTime;
    
    @Column(name = "end_time")
    private Time endTime;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "employee_id")
    private Integer employeeId;
    
    @Column(name = "firm_id")
    private Integer firmId;

    public Term(Integer termId, Date date, Time startTime, Time endTime, String description, Integer employeeId, Integer firmId) {
        this.termId = termId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.employeeId = employeeId;
        this.firmId = firmId;
    }
    
    public Term() {}

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
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
    
    
}
