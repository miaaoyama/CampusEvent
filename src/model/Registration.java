/* Registration Class: 
Work in progress; Ideas welcome */

package model;
import java.time.LocalDateTime;												// Used to grab the exact time when called

public class Registration {

	// Constants 
	public static final String REGISTERED = "Registered";					// Good to have these terms as constants
	public static final String CANCELLED = "Cancelled";
	
	// Data field members
	private Student student;												// Student object - not functional yet
    private Event event;													// Event object - not functional yet
	private LocalDateTime registrationDate;									// Registration date and time for the event
	private String registrationStatus;										// Shows if a student is currently registered for the chosen event

    // Constructor
	public Registration(Student student, Event event) {
		this.student = student;
		this.event = event;													
		this.registrationDate = LocalDateTime.now();						// Sets the registration date to the current day and time (not currently formatted)
		this.registrationStatus = REGISTERED;								// Sets the student's current registration status. "Registered" by default
	}

	// Methods:

	// Getters
	public Student getStudent() {
		return student;
	}

	public Event getEvent() {
		return event;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public String getRegistrationStatus() {
		return registrationStatus;
	}

	// Method: Cancels registration for the current student and event
	// Note that this does not remove the student from a list-- it only
	// acknowledges the student has cancelled.
	public void cancelRegistration() {
		registrationStatus = CANCELLED;
	}

	
}