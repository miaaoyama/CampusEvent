package model;

/* Registration Class: 
Work in progress; Ideas welcome */

import java.time.LocalDateTime;												// Used to grab the exact time when called

public class Registration {

	// Constants 
	public static final String REGISTERED = "Registered";					// Good to have these terms as constants
	public static final String CANCELLED = "Cancelled";
	
	// Data field members
	private final Student student;											// Student object
    private final Event event;												// Event object
	private final LocalDateTime registrationDate;							// Registration date and time for the event
	private String registrationStatus;										// Shows if a student is currently registered for the chosen event

    // Constructor
	public Registration(Student student, Event event) {

		// Exception check for if a student object exists
		if (student == null) {
			throw new IllegalArgumentException("Error: Student can't be null.");
		}
		
		// Exception check for if an event object exists
		if (event == null) {
			throw new IllegalArgumentException("Error: Event can't be null.");
		}
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

	// Method: Marks a student registration as cancelled
	public boolean cancelRegistration() {
		// If already cancelled, returns false.
		if (CANCELLED.equals(registrationStatus)) {
			return false;
		}
		registrationStatus = CANCELLED;
		return true;
	}

	// Method: prints relevant Registration info
	@Override
	public String toString() {
		return "Student: " + student.getName() + ", Event: " + event.getName()
				+ ", Registration date: " + registrationDate + ", Status: " + registrationStatus;
	}
}	// end class