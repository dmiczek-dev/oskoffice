package pl.kursant.oskoffice.service;

import java.util.List;
import pl.kursant.oskoffice.model.Vehicle;

public interface VehicleService {
    
    List<Vehicle> getVehicle();
    
    Vehicle addVehicle(Vehicle vehicle);
    
    Vehicle getVehicleById(Integer vehicleId);
    
    Vehicle updateVehicle(Vehicle vehicle);
    
    void deleteVehicle(Integer userId);          
    
}
