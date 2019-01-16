package com.foo.bar.hubjetcc.adapters;

import com.foo.bar.hubjetcc.model.ChargingStation;
import com.foo.bar.hubjetcc.ports.ChargingStationDao;

public class DummyChargingStationsDao implements ChargingStationDao {

    @Override
    public UpsertResult upsertChargingStation(ChargingStation chargingStation) {
        return null;
    }
}
