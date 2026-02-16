package parkingLot.factories;

import parkingLot.models.SpotAssignmentStrategyType;
import parkingLot.strategies.spotAssignmentStrategy.CheapestSpotAssignmentStrategy;
import parkingLot.strategies.spotAssignmentStrategy.RandomSpotAssignmentStrategy;
import parkingLot.strategies.spotAssignmentStrategy.SpotAssignmentStrategy;

public class SpotAssignmentStratergyFactory {
    public static SpotAssignmentStrategy getSpotAssignmentStrategy(SpotAssignmentStrategyType spotAssignmentStrategyType){
        if(spotAssignmentStrategyType.equals(SpotAssignmentStrategyType.CHEAPEST)){
            return new CheapestSpotAssignmentStrategy();
        }else if(spotAssignmentStrategyType.equals(SpotAssignmentStrategyType.RANDOM)){
            return new RandomSpotAssignmentStrategy();
        }
        return null;
    }
}
