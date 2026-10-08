/* RegistrationSystem Class: 
Work in progress; Ideas welcome */

package service;
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

	//  Method - Add Registration (W.I.P.)
	//	Currently: just adds a registration object to the array
	//  Eventually: checks for valid student name, valid event name, if already registered, and if capacity is full
	//  Adds a new registration if these checks are passed
	public void addRegistration(Registration registration) {
		if (registration == null) {
			throw new IllegalArgumentException("Registration can't be null.");
		}
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
		if (registration == null) {
			return false;
		}
		return registrations.remove(registration);
	}
	
	// Method - Show Events For Student
	public void showEventsForStudent(Student student) {
		// Exception check for if the student exists
		if (student == null) {
			throw new IllegalArgumentException("Student can't be null.");
		}

		// Stays false as long as no events are found for a student
		boolean found = false;
		// Cycles through every registration and finds the ones that the student is enrolled in
		// without having already cancelled. Then prints the events to the console
		for (Registration registration : registrations) {
			if (registration.getStudent().getStudentId() == student.getStudentId() 
				&& Registration.REGISTERED.equals(registration.getRegistrationStatus())) {
					System.out.println(registration.getEvent());
					found = true;
			}
		}
		// If no events are found for the student
		if (!found) {
			System.out.println("This student is not registered for any events.");
		}
	}

	// Method - Show Students for Event
	public void showStudentsForEvent(Event event) {
		// Exception check for if the event exists
		if (event == null) {
			throw new IllegalArgumentException("Event can't be null.");
		}
		// Stays false as long as no students are found for an event
		boolean found = false;

		// Cycles through every registration and checks if it matches the chosen event, then
		// checks that the student is still registered and hasn't cancelled.
		for (Registration registration : registrations) {
			if (registration.getEvent().getEventId() == event.getEventId()
				&& Registration.REGISTERED.equals(registration.getRegistrationStatus())) {
					Student student = registration.getStudent();
					// If we want to print out all the student parameters, use this guy:
					System.out.println(student);
					// If we just want to print out the student name, use this guy instead:
					// System.out.println(student.getName());
					found = true;
			}
		}
		// If no students are found for the event
		if (!found) {
			System.out.println("No students are currently registered for this event.");
		}
	}

}