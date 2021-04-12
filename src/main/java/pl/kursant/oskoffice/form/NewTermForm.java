package pl.kursant.oskoffice.form;

import java.sql.Date;

public class NewTermForm {
    
//    Atrybuty z term
    private Integer termId;
    private Integer employeeId;
    private Integer firmId;
    private Date date;
    private String startTime;
    private String endTime;
    private String description;
    
//    Atrybuty z practice_term
    private Integer studentId;
    private Integer vehicleId;
    
//    Atrybut dodatkowy
    private String type;

    public NewTermForm(Integer termId, Integer employeeId, Integer firmId, Date date, String startTime, String endTime, String description, Integer studentId, Integer vehicleId, String type) {
        this.termId = termId;
        this.employeeId = employeeId;
        this.firmId = firmId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.studentId = studentId;
        this.vehicleId = vehicleId;
        this.type = type;
    }
    
    public NewTermForm() {}

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    
    
}