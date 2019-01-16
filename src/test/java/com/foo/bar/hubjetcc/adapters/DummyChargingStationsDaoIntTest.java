package com.foo.bar.hubjetcc.adapters;

import com.foo.bar.hubjetcc.model.ChargingStation;
import com.foo.bar.hubjetcc.model.LatLon;
import com.foo.bar.hubjetcc.ports.ChargingStationDao;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

//This is example of integration test for adapter. Of course for such a dummy implmention it makes no sense to test it
// but it is still here to show how i believe it shall be done
public class DummyChargingStationsDaoIntTest {
    private final ChargingStation sampleChargingStation = new ChargingStation("1", new LatLon(1.2, 2.3), "12121");
    private final DummyChargingStationsDao sut = new DummyChargingStationsDao();
    @Test
    public void shallReturnCreatedIfNoChargingStationWithGivenIdAlreadyExists() {
        ChargingStationDao.UpsertResult upsertResult = sut.upsertChargingStation(sampleChargingStation);

        assertThat(upsertResult,is(ChargingStationDao.UpsertResult.CREATED));
    }

    @Test
    public void shallReturnUpdatedIfChargingStationWithGivenIdAlreadyExists() {
        sut.upsertChargingStation(sampleChargingStation);

        ChargingStationDao.UpsertResult upsertResult = sut.upsertChargingStation(sampleChargingStation);

        assertThat(upsertResult,is(ChargingStationDao.UpsertResult.UPDATED));
    }

    @Test
    public void shallReturnSomeWhenChargingStationWithGivenIdExists() {
        sut.upsertChargingStation(sampleChargingStation);

        Optional<ChargingStation> chargingStationById = sut.getChargingStationById(sampleChargingStation.getGuid());

        assertThat(chargingStationById, is(Optional.of(sampleChargingStation)));
    }

    @Test
    public void shallReturnNoneWhenChargingStationWithGivenIdNotExists() {
        Optional<ChargingStation> chargingStationById = sut.getChargingStationById("foobar");

        assertThat(chargingStationById, is(Optional.empty()));
    }



}