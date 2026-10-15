package service;

import java.util.ArrayList;
import model.Organizer;

public class OrganizerManager {

    private final ArrayList<Organizer> organizers;

    public OrganizerManager() 
    {
        organizers = new ArrayList<>();
    }

    // Add an organizer if the ID is unique.
    public boolean addOrganizer(Organizer organizer) 
    {
        if (organizer == null || findOrganizerById(organizer.getOrganizerId()) != null) 
        {
            return false;
        }

        organizers.add(organizer);
        return true;
    }

    // Find an organizer using their ID.
    public Organizer findOrganizerById(int organizerId) 
    {
        for (Organizer organizer : organizers) {
            if (organizer.getOrganizerId() == organizerId) 
            {
                return organizer;
            }
        }

        return null;
    }

    public ArrayList<Organizer> findOrganizersByAssociation(String Association) {
        ArrayList<Organizer> matches = new ArrayList<>();

        for (Organizer organizer : organizers)
        {
            if (organizer.getAssociation().toLowerCase().contains(Association.toLowerCase()))
            {
            matches.add(organizer);
            }
        }

        return matches;
    }

    // Return a copy of the organizer list.
    public ArrayList<Organizer> getAllOrganizers() 
    {
        return new ArrayList<>(organizers);
    }

    // Display organizers and their associated events.
    public void displayAllOrganizers(EventSystem eventSystem) 
    {
        if (organizers.isEmpty()) 
        {
            System.out.println("No organizers found.");
            return;
        }

        for (Organizer organizer : organizers) 
        {
            System.out.println("Organizer ID: " + organizer.getOrganizerId());
            System.out.println("Name: " + organizer.getName());
            System.out.println("Email: " + organizer.getEmail());
            System.out.println("Assosiate of: " + organizer.getAssociation());
            System.out.println("Organized Events:");

            if (organizer.getEventIds().isEmpty()) 
            {
                System.out.println(" No Organized Events.");
            }
            else
            {
                for (int eventId : organizer.getEventIds()) 
                {
                    model.Event event = eventSystem.findEventById(eventId);

                    if (event != null) 
                    {
                        System.out.println("  - " + event.getName() + " ID: " + eventId + );
                    } 
                    else
                    {
                        System.out.println("  - Event ID " + eventId + " not found");
                    }
                }
            }
        }
    }
}