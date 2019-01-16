package com.foo.bar.hubjetcc.usecases;

import com.foo.bar.hubjetcc.model.ChargingStation;
import com.foo.bar.hubjetcc.ports.ChargingStationDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CreateChargingStationTest {
    @Mock
    private ChargingStationDao mockedChargingStationDao;

    private CharginStationRequest someChargingStationRequest = new CharginStationRequest("someId", 13.413215, 52.521918, "10178");

    @Test
    public void shallAddNewBrandingChargingStation() {
        CreateChargingStation sut = new CreateChargingStation(mockedChargingStationDao);
        when(mockedChargingStationDao.upsertChargingStation(Mockito.any(ChargingStation.class))).thenReturn(ChargingStationDao.UpsertResult.CREATED);
        ServiceResponse serviceResponse = sut.addChargingStation(someChargingStationRequest);


        assertThat(serviceResponse, is(new ServiceResponse.EntityCreatedResponse("chargingStation", someChargingStationRequest.getGuid())));
    }

    @Test
    public void shallUpdateExistingChargingStation() {
        CreateChargingStation sut = new CreateChargingStation(mockedChargingStationDao);
        when(mockedChargingStationDao.upsertChargingStation(Mockito.any(ChargingStation.class))).thenReturn(ChargingStationDao.UpsertResult.UPDATED);
        ServiceResponse serviceResponse = sut.addChargingStation(someChargingStationRequest);

        assertThat(serviceResponse, is(ServiceResponse.OkResponse.Empty));
    }

}
