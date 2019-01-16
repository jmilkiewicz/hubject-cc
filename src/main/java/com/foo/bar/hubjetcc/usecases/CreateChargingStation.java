package com.foo.bar.hubjetcc.usecases;

import com.foo.bar.hubjetcc.model.ChargingStation;
import com.foo.bar.hubjetcc.model.LatLon;
import com.foo.bar.hubjetcc.ports.ChargingStationDao;

import java.util.Optional;

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

    public ServiceResponse getChargingStationById(String chargingStationId) {
        Optional<ChargingStation> chargingStationById = chargingStationDao.getChargingStationById(chargingStationId);

        return chargingStationById
                .<ServiceResponse>map(ServiceResponse.OkResponse::new)
                .orElse(ServiceResponse.NOT_FOUND);
    }

    public ServiceResponse getChargingStationByPostCode(String somePostCode) {
        return null;
    }
}
