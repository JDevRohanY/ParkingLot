package parkingLot.exceptions;

import parkingLot.repositories.GateRepository;

public class GateNotFoundException extends Exception{
    public GateNotFoundException(String message){
        super(message);
    }
}
