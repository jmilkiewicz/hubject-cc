package com.foo.bar.hubjetcc.adapters;

import com.foo.bar.hubjetcc.model.ChargingStation;
import com.foo.bar.hubjetcc.ports.ChargingStationDao;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class DummyChargingStationsDao implements ChargingStationDao {
    private final ConcurrentMap<String, ChargingStation> storage = new ConcurrentHashMap<>();

    @Override
    public List<ChargingStation> getChargingStationByPostCode(String postCode) {
        return storage.values().stream()
                .filter(chargingStation -> postCode.equals(chargingStation.getPostCode()))
                .collect(Collectors.toList());
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
