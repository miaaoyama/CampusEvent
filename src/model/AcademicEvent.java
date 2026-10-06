package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class AcademicEvent extends Event {

    private String subject;

    public AcademicEvent(int eventId, String name, String description,
                         LocalDate date, LocalTime time,
                         String location, int capacity,
                         String subject) {

        super(eventId, name, description, date, time, location, capacity);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public void displayDetails() {
        System.out.println("Academic Event");
        System.out.println("ID: " + getEventId());
        System.out.println("Name: " + getName());
        System.out.println("Description: " + getDescription());
        System.out.println("Date: " + getDate());
        System.out.println("Time: " + getTime());
        System.out.println("Location: " + getLocation());
        System.out.println("Capacity: " + getCapacity());
        System.out.println("Subject: " + subject);
    }
}