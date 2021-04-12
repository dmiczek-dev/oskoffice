
package pl.kursant.oskoffice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "vehicle")
public class Vehicle {
    
    @Id
    @SequenceGenerator(sequenceName="vehicle_vehicle_id_seq", name="vehicle_vehicle_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "vehicle_vehicle_id_seq")
    @Column(name = "vehicle_id")
    private Integer vehicleId;
    
    @Column(name = "vehicle_code")
    private String vehicleCode;
    
    @Column(name = "model")
    private String model;

    public Vehicle(Integer vehicleId, String vehicleCode, String model) {
        this.vehicleId = vehicleId;
        this.vehicleCode = vehicleCode;
        this.model = model;
    }
    
    public Vehicle() {}

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleCode() {
        return vehicleCode;
    }

    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    
    
    
}
