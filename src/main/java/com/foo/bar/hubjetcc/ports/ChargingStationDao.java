package com.foo.bar.hubjetcc.ports;

import com.foo.bar.hubjetcc.model.ChargingStation;

import java.util.List;
import java.util.Optional;

public interface ChargingStationDao {
    List<ChargingStation> getChargingStationByPostCode(String postCode);

    static enum UpsertResult {
        UPDATED, CREATED
    }

    UpsertResult upsertChargingStation(ChargingStation chargingStation);
    Optional<ChargingStation> getChargingStationById(String chargingStationId);
}
