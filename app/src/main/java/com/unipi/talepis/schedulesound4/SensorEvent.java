package com.unipi.talepis.schedulesound4;

import android.location.Location;

/**
 * Created by timmy on 10/12/2017.
 */

public class SensorEvent {
    private Location eventLocation;
    private String eventTimestamp;
    private String recognized_voice;

    public Location getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(Location eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(String eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public String getSensor_soundAverage() {
        return recognized_voice;
    }

    public void setRecognized_voice(String recognized_voice) {
        this.recognized_voice = recognized_voice;
    }

    public SensorEvent(Location eventLocation, String eventTimestamp, String recognized_voice) {
        this.eventLocation = eventLocation;
        this.eventTimestamp = eventTimestamp;
        this.recognized_voice = recognized_voice;
    }
}
