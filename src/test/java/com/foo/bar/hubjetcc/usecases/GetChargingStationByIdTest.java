package com.foo.bar.hubjetcc.usecases;

import com.foo.bar.hubjetcc.model.ChargingStation;
import com.foo.bar.hubjetcc.model.LatLon;
import com.foo.bar.hubjetcc.ports.ChargingStationDao;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;


public class GetChargingStationByIdTest {

    private ChargingStationDao mockedChargingStationDao = Mockito.mock(ChargingStationDao.class);
    private CreateChargingStation sut = new CreateChargingStation(mockedChargingStationDao);

    @Test
    public void shallReturnNotFoundIfChargingStationWithGivenIdNotExists() {
        recordGetChargingStationByIdWith(Optional.empty());

        ServiceResponse serviceResponse = sut.getChargingStationById("someId");

        assertThat(serviceResponse, is(ServiceResponse.NOT_FOUND));
    }

    @Test
    public void shallReturnOkWithChargingStationIfItExists() {
        String chargingStationId = "id";
        ChargingStation aSampleChargingStation = new ChargingStation(chargingStationId, new LatLon(1,2),"80323");
        recordGetChargingStationByIdWith(Optional.of(aSampleChargingStation));

        ServiceResponse serviceResponse = sut.getChargingStationById(chargingStationId);

        assertThat(serviceResponse, is(new ServiceResponse.OkResponse(aSampleChargingStation)));
    }

    private void recordGetChargingStationByIdWith(Optional<ChargingStation> chargingStation) {
        when(mockedChargingStationDao.getChargingStationById(Mockito.anyString())).thenReturn(chargingStation);
    }

}
