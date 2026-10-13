package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.Event;
import model.Registration;
import model.Student;

public class ReportSystem {

    private final EventSystem eventSystem;
    private final RegistrationSystem registrationSystem;

    public ReportSystem(EventSystem eventSystem,
                        RegistrationSystem registrationSystem) {
        this.eventSystem = eventSystem;
        this.registrationSystem = registrationSystem;
    }

    // Count active registrations for an event.
    private int countAttendees(int eventId) {
        Set<Integer> studentIds = new HashSet<>();

        for (Registration registration :
                registrationSystem.getAllRegistrations()) {

            if (registration.getEvent().getEventId() == eventId
                    && Registration.REGISTERED.equals(
                            registration.getRegistrationStatus())) {

                studentIds.add(
                    registration.getStudent().getStudentId()
                );
            }
        }

        return studentIds.size();
    }

    // Report 1: Events with available spots.
    public void displayAvailableEvents() {
        System.out.println("\nEvents with Available Capacity:");

        boolean found = false;

        for (Event event : eventSystem.getAllEvents()) {
            int registered = countAttendees(event.getEventId());
            int available = event.getCapacity() - registered;

            if (available > 0) {
                System.out.println(event.getName()
                        + " (ID: " + event.getEventId() + ")"
                        + " - " + available + (available == 1 ? " spot available" : " spots available"));
                found = true;
            }
        }

        if (!found) {
            System.out.println("No events with available spots.");
        }
    }

    // Report 2: Full events.
    public void displayFullEvents() {
        System.out.println("\nFull Events:");

        boolean found = false;

        for (Event event : eventSystem.getAllEvents()) {
            int registered = countAttendees(event.getEventId());

            if (registered >= event.getCapacity()) {
                System.out.println(event.getName()
                        + " (ID: " + event.getEventId() + ")"
                        + " - " + registered + "/"
                        + event.getCapacity() + " registered");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No full events found.");
        }
    }

    // Report 3: Most popular events.
    public void displayPopularEvents() {
        System.out.println("\nMost Popular Events:");

        ArrayList<Event> events = eventSystem.getAllEvents();

        if (events.isEmpty()) {
            System.out.println("No events found.");
            return;
        }

        events.sort((a, b) -> Integer.compare(
                countAttendees(b.getEventId()),
                countAttendees(a.getEventId())
        ));

        for (Event event : events) {
            System.out.println(event.getName()
                    + " (ID: " + event.getEventId() + ")"
                    + " - " + countAttendees(event.getEventId())
                    + (countAttendees(event.getEventId()) == 1
                        ? " registration" : " registrations"));
        }
    }

    // Report 4: Students registered for multiple events.
    public void displayStudentsWithMultipleEvents() {
        System.out.println("\nStudents Registered for Multiple Events:");

        Map<Integer, Set<Integer>> studentEvents = new HashMap<>();
        Map<Integer, Student> students = new HashMap<>();

        for (Registration registration :
                registrationSystem.getAllRegistrations()) {

            if (!Registration.REGISTERED.equals(
                    registration.getRegistrationStatus())) {
                continue;
            }

            Student student = registration.getStudent();
            int studentId = student.getStudentId();

            students.put(studentId, student);

            studentEvents.computeIfAbsent(
                    studentId, id -> new HashSet<>()
            ).add(registration.getEvent().getEventId());
        }

        boolean found = false;

        for (Map.Entry<Integer, Set<Integer>> entry :
                studentEvents.entrySet()) {

            int count = entry.getValue().size();

            if (count >= 2) {
                Student student = students.get(entry.getKey());

                System.out.println(student.getName()
                        + " (ID: " + student.getStudentId() + ")"
                        + " - " + count + " events");
                found = true;
            }
        }

        if (!found) {
            System.out.println(
                "No students registered for multiple events."
            );
        }
    }
}
