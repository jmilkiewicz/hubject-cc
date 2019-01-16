package com.foo.bar.hubjetcc.usecases;

import com.foo.bar.hubjetcc.ports.ChargingStationDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class CreateChargingStationTest {
    @Mock
    private ChargingStationDao mockedChargingStationDao;

    private CreateChargingStation sut = new CreateChargingStation(mockedChargingStationDao);

    @Test
    public void shallAddNewBrandingChargingStation() {
        CharginStationRequest charginStationRequest = new CharginStationRequest("someId", 13.413215, 52.521918, "10178");
        ServiceResponse serviceResponse = sut.addChargingStation(charginStationRequest);

        assertThat(serviceResponse, is(ServiceResponse.OK));
    }
}
