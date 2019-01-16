package com.foo.bar.hubjetcc.adapters;

import com.foo.bar.hubjetcc.model.ChargingStation;
import com.foo.bar.hubjetcc.ports.ChargingStationDao;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DummyChargingStationsDao implements ChargingStationDao {
    private final ConcurrentMap<String, ChargingStation> storage = new ConcurrentHashMap<>();

    @Override
    public List<ChargingStation> getChargingStationByPostCode(String anyString) {
        return null;
    }

    @Override
    public UpsertResult upsertChargingStation(ChargingStation chargingStation) {
        ChargingStation prev = storage.put(chargingStation.getGuid(), chargingStation);
        if(prev == null){
            return UpsertResult.CREATED;
        }
        return UpsertResult.UPDATED;
    }

    @Override
    public Optional<ChargingStation> getChargingStationById(String chargingStationId) {
        ChargingStation chargingStation = storage.get(chargingStationId);
        return Optional.ofNullable(chargingStation);
    }
}
