
package pl.kursant.oskoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "firm")
public class Firm {
    
    @Id
    @SequenceGenerator(sequenceName="firm_firm_id_seq", name="firm_firm_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "firm_firm_id_seq")
    @Column(name = "firm_id")
    private Integer firmId;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "street")
    private String street;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "ZIP_code")
    private String zipCode;

    public Firm(Integer firmId, String name, String street, String city, String zipCode) {
        this.firmId = firmId;
        this.name = name;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }
    
    public Firm() {}

    public Integer getFirmId() {
        return firmId;
    }

    public void setFirmId(Integer firmId) {
        this.firmId = firmId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    
    
}
