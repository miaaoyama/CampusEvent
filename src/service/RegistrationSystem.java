/* RegistrationSystem Class: 
Work in progress; Ideas welcome */

import java.util.ArrayList;

public class RegistrationSystem {

	// Data field - holds an array of Registration objects
	private ArrayList<Registration> registrations;

	// Constructor - creates a new (empty) array of Registration objects
	public RegistrationSystem() {
		registrations = new ArrayList<>();
	}

	//  Method - Add Registration (W.I.P.)
	//	Currently: just adds a registration object to the array
	//  Eventually: checks for valid student name, valid event name, if already registered, and if capacity is full
	//  Adds a new registration if these checks are passed
	public void addRegistration(Registration registration) {
		registrations.add(registration);
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
		return registrations.remove(registration);
	}
	
	// View a student's registered events
	
	// View all students registered for an event
}