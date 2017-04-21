package com.imaginecup.bug5.vista.dao;

/**
 * Created by kataii on 4/21/2017.
 */

public class SmartHome {
    private String id;
    private String createdAt;
    private String updatedAt;
    private boolean version;
    private String deviceType;
    private boolean status;

    public String getId() {

        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public boolean isVersion() {
        return version;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public boolean isStatus() {
        return status;
    }
}
