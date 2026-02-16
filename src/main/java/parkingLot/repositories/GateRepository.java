package parkingLot.repositories;

import parkingLot.models.Gate;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GateRepository {
    private Map<Long, Gate> gateMap;
    private Long id;

    public Map<Long, Gate> getGateMap() {
        return gateMap;
    }

    public void setGateMap(Map<Long, Gate> gateMap) {
        this.gateMap = gateMap;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GateRepository() {
        gateMap = new HashMap<>();
        id = 0L;
    }

    public Gate save(Gate gate){
        //Update + Inset => Upsert
        if(gate.getId() == 0){
            //This means insert
            id = id + 1;
            gate.setId(id);
            gateMap.put(id, gate);
            return gate;
        }
        gateMap.put(gate.getId(), gate);
        return gate;
    }

    public Optional<Gate> findById(Long gateId){
        if(getGateMap().containsKey(gateId)){
            return Optional.of(gateMap.get(gateId));
        }
        return Optional.empty();
    }
}
