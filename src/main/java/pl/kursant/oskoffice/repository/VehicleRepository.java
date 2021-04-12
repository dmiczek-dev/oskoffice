package pl.kursant.oskoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.kursant.oskoffice.model.Vehicle;


@Transactional
public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{
    
    
}
