package com.foo.bar.hubjetcc.ports;

import com.foo.bar.hubjetcc.model.ChargingStation;

public interface ChargingStationDao {

    public static enum UpsertResult {
        UPDATED, CREATED
    }

    public UpsertResult upsertChargingStation(ChargingStation chargingStation);
}
