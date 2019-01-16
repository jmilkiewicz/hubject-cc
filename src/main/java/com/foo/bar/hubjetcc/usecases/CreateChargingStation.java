package com.foo.bar.hubjetcc.usecases;

import com.foo.bar.hubjetcc.model.ChargingStation;
import com.foo.bar.hubjetcc.model.LatLon;
import com.foo.bar.hubjetcc.ports.ChargingStationDao;

public class CreateChargingStation {
    private final ChargingStationDao chargingStationDao;

    public CreateChargingStation(ChargingStationDao chargingStationDao) {
        this.chargingStationDao = chargingStationDao;
    }

    public ServiceResponse addChargingStation(CreateChargingStationInput charginStationRequest) {
        ChargingStationDao.UpsertResult upsertResult = chargingStationDao.upsertChargingStation(
                new ChargingStation(
                        charginStationRequest.getGuid(),
                        new LatLon(charginStationRequest.getLat(), charginStationRequest.getLon()),
                        charginStationRequest.getPostCode()));
        if(ChargingStationDao.UpsertResult.CREATED == upsertResult){
            return new ServiceResponse.EntityCreatedResponse("chargingStation", charginStationRequest.getGuid());
        }
        return ServiceResponse.OkResponse.Empty;

    }
}
