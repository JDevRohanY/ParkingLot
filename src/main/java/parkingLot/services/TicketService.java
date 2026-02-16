package parkingLot.services;

import parkingLot.exceptions.GateNotFoundException;
import parkingLot.exceptions.ParkingLotNotFoundException;
import parkingLot.exceptions.SpotNotAvailableException;
import parkingLot.factories.SpotAssignmentStratergyFactory;
import parkingLot.models.*;
import parkingLot.repositories.GateRepository;
import parkingLot.repositories.ParkingLotRepository;
import parkingLot.repositories.TicketRepository;
import parkingLot.repositories.VehicleRepository;
import parkingLot.strategies.spotAssignmentStrategy.SpotAssignmentStrategy;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.Optional;

public class TicketService {
    private GateRepository gateRepository;
    private ParkingLotRepository parkingLotRepository;
    private VehicleRepository vehicleRepository;
    private TicketRepository ticketRepository;

    public TicketService(GateRepository gateRepository, ParkingLotRepository parkingLotRepository, VehicleRepository vehicleRepository, TicketRepository ticketRepository) {
        this.gateRepository = gateRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.vehicleRepository = vehicleRepository;
        this.ticketRepository = ticketRepository;
    }

    public Ticket issueTicket(Long gateId,
                              String vehicleNumber,
                              String ownerName,
                              VehicleType vehicleType) throws GateNotFoundException, ParkingLotNotFoundException, SpotNotAvailableException {
        Ticket ticket = new Ticket();
        //To fill all the details in ticket we need
        //number, entry time, vehicle, parkingSpot, generateAt
        ticket.setEntryTime(new Date());

        Optional<Gate> optionalGate = gateRepository.findById(gateId);

        if (optionalGate.isEmpty()) {
            throw new GateNotFoundException("Gate with ID : " + gateId + " not found.");
        }

        Gate gate = optionalGate.get();
        ticket.setGeneratedAt(gate);

        Optional<Vehicle> optionalVehicle = vehicleRepository.findByVehicleNumber(vehicleNumber);
        Vehicle savedVehicle = null;

        if (optionalVehicle.isEmpty()) {
            // create a new vehicle in the DB
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleNumber(vehicleNumber);
            vehicle.setOwnerName(ownerName);
            vehicle.setVehicleType(vehicleType);
            savedVehicle = vehicleRepository.save(vehicle);
        } else {
            savedVehicle = optionalVehicle.get();
        }

        ticket.setVehicle(savedVehicle);
        ticket.setNumber("Ticket Number ---");

        Optional<ParkingLot> optionalParkingLot = parkingLotRepository.findByGateId(gateId);
        if (optionalParkingLot.isEmpty()) {
            throw new ParkingLotNotFoundException("Parking lot not found, invalid parking lot.");
        }

        ParkingLot parkingLot = optionalParkingLot.get();

        SpotAssignmentStrategy spotAssignmentStrategyType = SpotAssignmentStratergyFactory.getSpotAssignmentStrategy(parkingLot.getSpotAssignmentStrategyType());

        ParkingSpot parkingSpot = spotAssignmentStrategyType.assignSpot(vehicleType, gate);

        if (parkingSpot.getParkingSpotStatus().equals(ParkingSpotStatus.NOT_AVAILABLE)) {
            throw new SpotNotAvailableException("Spot not available.");
        }

        ticket.setParkingSpot(parkingSpot);

        return ticketRepository.save(ticket);

    }
}
