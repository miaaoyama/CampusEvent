package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class CareerEvent extends Event {

    private String companyName;

    public CareerEvent(int eventId, String name, String description,
                       LocalDate date, LocalTime time,
                       String location, int capacity,
                       String companyName) {

        super(eventId, name, description, date, time, location, capacity);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public void displayDetails() {
        System.out.println("Career Event");
        System.out.println("ID: " + getEventId());
        System.out.println("Name: " + getName());
        System.out.println("Description: " + getDescription());
        System.out.println("Date: " + getDate());
        System.out.println("Time: " + getTime());
        System.out.println("Location: " + getLocation());
        System.out.println("Capacity: " + getCapacity());
        System.out.println("Company: " + companyName);
    }
}