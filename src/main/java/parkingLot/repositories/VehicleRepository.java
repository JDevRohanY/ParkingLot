package parkingLot.repositories;

import parkingLot.models.Vehicle;

import javax.swing.text.html.Option;
import java.util.Map;
import java.util.Optional;

public class VehicleRepository {
    Map<Long, Vehicle> vehicleMap;

    public Optional<Vehicle> findByVehicleNumber(String vehicleNumber){
        return null;
    }

    public Optional<Vehicle> findById(Long vehicleId){
        return null;
    }

    public Vehicle save(Vehicle vehicle){
        return null;
    }
}
