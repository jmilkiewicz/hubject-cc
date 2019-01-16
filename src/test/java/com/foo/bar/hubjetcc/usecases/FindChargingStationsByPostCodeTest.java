package com.foo.bar.hubjetcc.usecases;

import com.foo.bar.hubjetcc.model.ChargingStation;
import com.foo.bar.hubjetcc.model.LatLon;
import com.foo.bar.hubjetcc.ports.ChargingStationDao;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class FindChargingStationsByPostCodeTest {
    private ChargingStationDao mockedChargingStationDao = Mockito.mock(ChargingStationDao.class);
    private CreateChargingStation sut = new CreateChargingStation(mockedChargingStationDao);

    @Test
    public void shallReturnOKWithDataReturnedChargingStation() {

        List<ChargingStation> chargingStations = Arrays.asList(
                new ChargingStation("id1", new LatLon(1, 2.3), "80323"),
                new ChargingStation("id2", new LatLon(1.2, 2), "80323"));

        when(mockedChargingStationDao.getChargingStationByPostCode(Mockito.anyString())).thenReturn(chargingStations);

        ServiceResponse serviceResponse = sut.getChargingStationByPostCode("somePostCode");

        assertThat(serviceResponse, is(new ServiceResponse.OkResponse(chargingStations)));
    }
}
