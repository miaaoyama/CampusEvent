package model;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Event {

    private int eventId;
    private String name;
    private String description;
    private LocalDate date;
    private LocalTime time;
    private String location;
    private int capacity;

    public Event(int eventId, String name, String description,
                 LocalDate date, LocalTime time,
                 String location, int capacity) {

        this.eventId = eventId;
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
        this.location = location;
        this.capacity = capacity;
    }

    public int getEventId() {
        return eventId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public abstract void displayDetails();
}