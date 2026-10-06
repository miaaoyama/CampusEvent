package service;

import java.util.ArrayList;
import model.Event;

public class EventSystem {

    private ArrayList<Event> events;

    public EventSystem() {
        events = new ArrayList<>();
    }

    // Add an event
    public void addEvent(Event event) {
        if (event != null) {
            events.add(event);
        }
    }

    // Find an event by its ID
    public Event findEventById(int eventId) {
        for (Event event : events) {
            if (event.getEventId() == eventId) {
                return event;
            }
        }

        return null;
    }

    // Search for events by name
    public ArrayList<Event> searchEventsByName(String name) {
        ArrayList<Event> matches = new ArrayList<>();

        for (Event event : events) {
            if (event.getName().toLowerCase().contains(name.toLowerCase())) {
                matches.add(event);
            }
        }

        return matches;
    }

    // Display every event
    public void displayAllEvents() {
        for (Event event : events) {
            event.displayDetails();
            System.out.println("--------------------");
        }
    }
}