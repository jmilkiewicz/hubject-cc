package com.foo.bar.hubjetcc.web;

import com.foo.bar.hubjetcc.ports.ChargingStationDao;
import com.foo.bar.hubjetcc.usecases.CreateChargingStation;
import com.foo.bar.hubjetcc.usecases.CreateChargingStationInput;
import com.foo.bar.hubjetcc.usecases.ServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("chargingStations")
public class ChargingStationsController {
    private final CreateChargingStation createChargingStation;
    private final ServiceResponse.Mapper<ResponseEntity> mapper = new ServiceResponseToControllerResponseMapper();

    public ChargingStationsController(ChargingStationDao chargingStationDao) {
        createChargingStation = new CreateChargingStation(chargingStationDao);
    }


    @PutMapping("{id}")
    public ResponseEntity createChargingStation(@PathVariable("id") String id, @RequestBody CreateChargingStationRequest createChargingStationRequest) {
        ServiceResponse serviceResponse = createChargingStation.addChargingStation(buildCreateChargingStationInput(id, createChargingStationRequest));
        return serviceResponse.map(mapper);
    }

    @GetMapping("{id}")
    public ResponseEntity getChargingStationById(@PathVariable("id") String id) {
        return createChargingStation.getChargingStationById(id).map(mapper);
    }

    @GetMapping()
    public ResponseEntity getChargingStationByPostCode(@RequestParam("postCode") String postCode) {
        return createChargingStation.getChargingStationByPostCode(postCode).map(mapper);
    }

    private CreateChargingStationInput buildCreateChargingStationInput(@PathVariable("id") String id, CreateChargingStationRequest createChargingStationRequest) {
        return new CreateChargingStationInput(id, createChargingStationRequest.getLat(), createChargingStationRequest.getLon(), createChargingStationRequest.getPostCode());
    }


}
