package com.foo.bar.hubjetcc.adapters;

import com.foo.bar.hubjetcc.model.ChargingStation;
import com.foo.bar.hubjetcc.model.LatLon;
import com.foo.bar.hubjetcc.ports.ChargingStationDao;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

//This is example of integration test for adapter. Of course for such a dummy implmention it makes no sense to test it
// but it is still here to show how i believe it shall be done
public class DummyChargingStationsDaoIntTest {
    private final ChargingStation sampleChargingStation = new ChargingStation("1", new LatLon(1.2, 2.3), "12121");

    @Test
    public void shallReturnCreatedIfNoChargingStationWithGivenIdAlreadyExists() {
        DummyChargingStationsDao dummyChargingStationsDao = new DummyChargingStationsDao();

        ChargingStationDao.UpsertResult upsertResult = dummyChargingStationsDao.upsertChargingStation(sampleChargingStation);

        assertThat(upsertResult,is(ChargingStationDao.UpsertResult.CREATED));
    }

}