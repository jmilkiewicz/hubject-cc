package com.foo.bar.hubjetcc.usecases;

import java.util.Objects;

public class CharginStationRequest {
    private final String guid;
    private final double lat;
    private final double lon;
    private final String postCode;

    public CharginStationRequest(String guid, double lat, double lon, String postCode) {
        this.guid = guid;
        this.lat = lat;
        this.lon = lon;
        this.postCode = postCode;
    }

    public String getGuid() {
        return guid;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getPostCode() {
        return postCode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharginStationRequest that = (CharginStationRequest) o;
        return Objects.equals(guid, that.guid) &&
                Objects.equals(lat, that.lat) &&
                Objects.equals(lon, that.lon) &&
                Objects.equals(postCode, that.postCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid, lat, lon, postCode);
    }
}
