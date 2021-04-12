
package pl.kursant.oskoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
    
    @Id
    @SequenceGenerator(sequenceName="student_student_id_seq", name="student_student_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "student_student_id_seq")
    @Column(name = "student_id")
    private Integer studentId;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "pkk_number")
    private String pkkNumber;
    
    @Column(name = "street")
    private String street;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "ZIP_code")
    private String zipCode;
    
    @Column(name = "user_id")
    private Integer userId;

    public Student(Integer studentId, String firstName, String lastName, String pkkNumber, String street, String city, String zipCode, Integer userId) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pkkNumber = pkkNumber;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.userId = userId;
    }

    public Student() {}
    
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPkkNumber() {
        return pkkNumber;
    }

    public void setPkkNumber(String pkkNumber) {
        this.pkkNumber = pkkNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    
    
}
