package com.foo.bar.hubjetcc.usecases;

import com.foo.bar.hubjetcc.ports.ChargingStationDao;

public class CreateChargingStation {
    private final ChargingStationDao chargingStationDao;

    public CreateChargingStation(ChargingStationDao chargingStationDao) {
        this.chargingStationDao = chargingStationDao;
    }

    public ServiceResponse addChargingStation(CharginStationRequest charginStationRequest) {
        return  null;
    }
}
