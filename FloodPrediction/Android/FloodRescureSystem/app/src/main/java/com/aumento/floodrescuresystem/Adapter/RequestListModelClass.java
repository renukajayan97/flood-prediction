package com.aumento.floodrescuresystem.Adapter;

public class RequestListModelClass {

    String id;
    String name;
    String location;
    String latitude;
    String longitude;

    public RequestListModelClass(String id, String name, String location, String latitude, String longitude) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
