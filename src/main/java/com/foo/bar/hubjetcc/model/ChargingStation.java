package com.foo.bar.hubjetcc.model;

import java.util.Objects;

public class ChargingStation {
    private final String guid;
    private final LatLon location;
    private final String postCode;

    public ChargingStation(String guid, LatLon location, String postCode) {
        this.guid = guid;
        this.location = location;
        this.postCode = postCode;
    }

    public String getGuid() {
        return guid;
    }

    public LatLon getLocation() {
        return location;
    }

    public String getPostCode() {
        return postCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChargingStation that = (ChargingStation) o;
        return Objects.equals(guid, that.guid) &&
                Objects.equals(location, that.location) &&
                Objects.equals(postCode, that.postCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid, location, postCode);
    }
}
