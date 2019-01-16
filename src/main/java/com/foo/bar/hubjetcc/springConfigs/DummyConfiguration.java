package com.foo.bar.hubjetcc.springConfigs;

import com.foo.bar.hubjetcc.adapters.DummyChargingStationsDao;
import com.foo.bar.hubjetcc.ports.ChargingStationDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DummyConfiguration {

    @Bean
    public ChargingStationDao myBean() {
        return new DummyChargingStationsDao();
    }
}
