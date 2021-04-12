package pl.kursant.oskoffice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kursant.oskoffice.model.Vehicle;
import pl.kursant.oskoffice.repository.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService{
    
    @Autowired
    VehicleRepository vehicleRepo;

    @Override
    public List<Vehicle> getVehicle() {
        return vehicleRepo.findAll();
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepo.saveAndFlush(vehicle);
    }

    @Override
    public Vehicle getVehicleById(Integer vehicleId) {
        return vehicleRepo.findOne(vehicleId);
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) {
        return vehicleRepo.saveAndFlush(vehicle);
    }

    @Override
    public void deleteVehicle(Integer userId) {
        vehicleRepo.delete(userId);
    }
    
}
