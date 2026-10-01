/* Registration Class: 
Work in progress; Ideas welcome */

import java.time.LocalDateTime;												// Used to grab the exact time of an input

public class Registration {
	private Student student;												// Student name
    private Event event;													// Event name
	private LocalDateTime regDate;											// Registration date and time for the event
	private String regStatus;												// Shows if a student is currently registered for the chosen event

    // Constructor
	public Registration(Student student, Event event) {
		this.student = student;
		this.event = event;													
		this.regDate = LocalDateTime.now();									// Sets the registration date to the current day and time (not currently formatted)
		this.regStatus = "Registered";										// Sets the student's current registration status. "Registered" by default
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
		return regDate;
	}

	public String getRegistrationStatus() {
		return regStatus;
	}

	// Method: Cancels registration for the current student and event
	public void cancelRegistration() {
		regStatus = "Cancelled";
	}

	
}