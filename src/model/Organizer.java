package model;

import java.util.ArrayList;

public class Organizer {

    private final int organizerId;
    private final String name;
    private final String email;
    private final ArrayList<Integer> eventIds;

    public Organizer(int organizerId, String name, String email) {
        this.organizerId = organizerId;
        this.name = name;
        this.email = email;
        this.eventIds = new ArrayList<>();
    }

    public int getOrganizerId() {
        return organizerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Integer> getEventIds() {
        return new ArrayList<>(eventIds);
    }

    public boolean addEvent(int eventId) {
        if (eventIds.contains(eventId)) {
            return false;
        }

        eventIds.add(eventId);
        return true;
    }
}
