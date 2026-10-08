package service;

/* RegistrationSystem Class: 
Work in progress; Ideas welcome */

import java.util.ArrayList;
import model.Registration;
import model.Event;
import model.Student;

public class RegistrationSystem {

	// Data field - holds an array of Registration objects
	private final ArrayList<Registration> registrations;

	// Constructor - creates a new (empty) array of Registration objects
	public RegistrationSystem() {
		registrations = new ArrayList<>();
	}

	// Method - Add Registration
	// Takes a student and event object as parameters, and performs several checks
	// on them before either adding a new registration or aborting the registration.
	// Before entering this call, the student ID and event ID have already been
	// confirmed to exist, so now this method will check the duplicity and capacity.
	// Returns true if registration is successfully created. Returns false otherwise.
	public boolean addRegistration(Student student, Event event) {
    	// First, check if either student or event are null
    	if (student == null || event == null) {
        	throw new IllegalArgumentException("Student and/or Event can't be null.");
    	}

    	// Next, check for a duplicate registration.
		// Iterate through the current registration array, checking each registration.
    	for (Registration registration : registrations) {
			// If both IDs match an already existing registration's IDs, and that registration is active, return false.
        	if (registration.getStudent().getStudentId() == student.getStudentId()
                	&& registration.getEvent().getEventId() == event.getEventId()
                	&& Registration.REGISTERED.equals(registration.getRegistrationStatus())) {
            	return false;
        	}
    	}

		// Next, check if there is available capacity in the event.
    	// Create a counter to count the students currently registered for the event.
    	int attendeeCount = 0;

		// Iterate through the current registration array, checking each registration.
    	for (Registration registration : registrations) {
			// If the ID of the desired event matches the event ID of a registration, and that registration is active, increment attendee counter.
        	if (registration.getEvent().getEventId() == event.getEventId()
                	&& Registration.REGISTERED.equals(registration.getRegistrationStatus())) {
            	attendeeCount++;
        	}
    	}

    	// Now that we know how many students are currently registered for the event, compare that amount to the capacity.
    	if (attendeeCount >= event.getCapacity()) {
        	return false;	// If already full, return false.
    	}

    	// Otherwise, duplicity and capacity checks were passed, so create and add the registration
    	Registration registration = new Registration(student, event);
    	registrations.add(registration);

    	return true;
	}

	// Method - Get Registration Count 
	// Currently: returns the number of registration objects in the array. 
	// Note: not necessarily the total number of students or events.
	public int getRegistrationCount() {
		return registrations.size();
	}

	// Method - Remove Registration
	// Currently: removes the provided registration object from the array
	// Eventually: checks if the registration provided exists, removes it if this check is passed
	public boolean removeRegistration(Registration registration) {
		if (registration == null) {
			return false;
		}
		return registrations.remove(registration);
	}
	
	// Method - View Registrations For Student
	public void viewStudentRegistrations(Student student) {
    	// Exception check for if the student exists
    	if (student == null) {
        	throw new IllegalArgumentException("Student can't be null.");
    	}

		// Print the student's name out
    	System.out.println("Student Name: " + student.getName() + "\n");
    	System.out.println("Registered Events:");

		// Stays false as long as no events are found for a student
    	boolean found = false;
    	int eventNumber = 1;	// Counter for displaying events in a numbered list

		// Cycles through every registration and finds the ones that the student is enrolled in
		// without having already cancelled. Then prints the events to the console
    	for (Registration registration : registrations) {
        	if (registration.getStudent().getStudentId() == student.getStudentId()
                && Registration.REGISTERED.equals(registration.getRegistrationStatus())) {
				// Print in the format: #. Event name
            	System.out.println(eventNumber + ". " + registration.getEvent().getName());
            	eventNumber++;
            	found = true;
        	}
    	}
		// If no events are found for the student
		if (!found) {
			System.out.println("This student is not registered for any events.");
		}
	}

	// Method - View Event Attendees
	public void viewEventAttendees(Event event) {
		// Exception check for if the event exists
		if (event == null) {
			throw new IllegalArgumentException("Event can't be null.");
		}

		// Print the event's name out
    	System.out.println("Event Name: " + event.getName() + "\n");
    	System.out.println("Registered Students:");

		// Stays false as long as no students are found for an event
		boolean found = false;
		int studentNumber = 1;	// Counter for displaying students in a numbered list

		// Cycles through every registration and checks if it matches the chosen event, then
		// checks that the student is still registered and hasn't cancelled.
		for (Registration registration : registrations) {
			if (registration.getEvent().getEventId() == event.getEventId()
				&& Registration.REGISTERED.equals(registration.getRegistrationStatus())) {
					Student student = registration.getStudent();
					// Print in the format: #. Student name
					System.out.println(studentNumber + ". " + registration.getStudent().getName());
					studentNumber++;
					found = true;
			}
		}
		// If no students are found for the event
		if (!found) {
			System.out.println("No students are currently registered for this event.");
		}
	}

}