package model;

import java.util.ArrayList;

public class Organizer {
	private int organizerId;
	private String name;
	private String email;
	private String association;
    private ArrayList<Integer> eventIds;

	public Organizer(int OragnizerId, String name, String email, String association) 
    {
		this.organizerId = OragnizerId;
		this.name = name;
		this.email = email;
		this.association = association;
        this.eventIds = new ArrayList<>();
	}

	public int getOrganizerId() 
    {
		return organizerId;
	}

	public String getName() 
    {
		return name;
	}

	public String getEmail() 
    {
		return email;
	}

	public String getAssociation() 
    {
		return association;
	}

    public ArrayList<Integer> getEventIds() 
    {
        return new ArrayList<>(eventIds);
    }

    public boolean addEvent(int eventId) 
    {
        if (eventIds.contains(eventId)) 
        {
            return false;
        }

        eventIds.add(eventId);
        return true;

	}

	// Override toString to help with printing an organizer object
	@Override
    public String toString() {
        return "Organizer ID: " + organizerId
                + "\nName: " + name
                + "\nEmail: " + email
                + "\nAssociation: " + association
                + "\nEvent IDs: " + eventIds;
    }
}