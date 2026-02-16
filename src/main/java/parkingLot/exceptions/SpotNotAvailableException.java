package parkingLot.exceptions;

import parkingLot.strategies.spotAssignmentStrategy.SpotAssignmentStrategy;

public class SpotNotAvailableException extends Exception{
    public SpotNotAvailableException(String message){
        super(message);
    }
}
