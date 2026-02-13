package parkingLot.models;

import java.util.List;

public class ParkingFloor extends BaseModals {
    private List<ParkingSpot> parkingFloorSpot;
    private ParkingFloorStatus parkingFloorStatus;
    private int floorNumber;

    public List<ParkingSpot> getParkingFloorSpot() {
        return parkingFloorSpot;
    }

    public void setParkingFloorSpot(List<ParkingSpot> parkingFloorSpot) {
        this.parkingFloorSpot = parkingFloorSpot;
    }

    public ParkingFloorStatus getParkingFloorStatus() {
        return parkingFloorStatus;
    }

    public void setParkingFloorStatus(ParkingFloorStatus parkingFloorStatus) {
        this.parkingFloorStatus = parkingFloorStatus;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }
}
