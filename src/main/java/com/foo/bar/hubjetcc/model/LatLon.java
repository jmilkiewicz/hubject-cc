package com.foo.bar.hubjetcc.model;

import java.util.Objects;

public class LatLon {
    private final double lat;
    private final double lon;

    public LatLon(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LatLon latLon = (LatLon) o;
        return Double.compare(latLon.lat, lat) == 0 &&
                Double.compare(latLon.lon, lon) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lat, lon);
    }
}
