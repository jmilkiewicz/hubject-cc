package com.foo.bar.hubjetcc.usecases;

import com.foo.bar.hubjetcc.model.ChargingStation;
import com.foo.bar.hubjetcc.ports.ChargingStationDao;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;


public class CreateChargingStationTest {

    private ChargingStationDao mockedChargingStationDao = Mockito.mock(ChargingStationDao.class);
    private CreateChargingStation sut = new CreateChargingStation(mockedChargingStationDao);

    private CharginStationRequest someChargingStationRequest = new CharginStationRequest("someId", 13.413215, 52.521918, "10178");

    @Test
    public void shallAddNewBrandingChargingStation() {
        recordUpsertWith(ChargingStationDao.UpsertResult.CREATED);
        ServiceResponse serviceResponse = sut.addChargingStation(someChargingStationRequest);

        ServiceResponse.EntityCreatedResponse expected = new ServiceResponse.EntityCreatedResponse("chargingStation", someChargingStationRequest.getGuid());
        assertThat(serviceResponse, is(expected));
    }

    @Test
    public void shallUpdateExistingChargingStation() {
        recordUpsertWith(ChargingStationDao.UpsertResult.UPDATED);
        ServiceResponse serviceResponse = sut.addChargingStation(someChargingStationRequest);

        assertThat(serviceResponse, is(ServiceResponse.OkResponse.Empty));
    }

    private void recordUpsertWith(ChargingStationDao.UpsertResult upsertResult) {
        when(mockedChargingStationDao.upsertChargingStation(Mockito.any(ChargingStation.class))).thenReturn(upsertResult);
    }

}
