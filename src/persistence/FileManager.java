
package persistence;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import model.Student;

import java.io.BufferedReader;
import java.io.FileReader;

import model.Event;
import model.AcademicEvent;
import model.ClubEvent;
import model.CareerEvent;
import model.SocialEvent;

import java.time.LocalDate;
import java.time.LocalTime;

import java.time.LocalDateTime;
import model.Registration;

public class FileManager {

    // Saves a list of students to a text file
    public void saveStudents(ArrayList<Student> students, String filename) {

        try (FileWriter writer = new FileWriter(filename)) {

            for (Student student : students) {
                writer.write(student.getStudentId() + "," + student.getName() + "," + student.getEmail() + "," + student.getMajor() + "\n");
            }

            System.out.println("Students saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }



    // Loads students from a text file
    public ArrayList<Student> loadStudents(String filename) {

        ArrayList<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            String line;

            // Read the file one line at a time
            while ((line = reader.readLine()) != null) {

                // Separate the student's information
                String[] data = line.split(",", -1);

                if (data.length != 4) {
                    throw new IllegalArgumentException("Invalid student record in " + filename);
                }

                try {
                    int studentId = Integer.parseInt(data[0]);
                    String name = data[1];
                    String email = data[2];
                    String major = data[3];

                    // Recreate the Student object
                    Student student = new Student(studentId, name, email, major);

                    // Add the student to the list
                    students.add(student);

                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid student ID in " + filename, e);
                }
            }

            System.out.println("Students loaded successfully.");

        } catch (IOException e) {
            throw new IllegalStateException("Error loading students: " + filename, e);
        }

        return students;
    }


    // Saves a list of events to a text file
    public void saveEvents(ArrayList<Event> events, String filename) {

        try (FileWriter writer = new FileWriter(filename)) {

            for (Event event : events) {

                // Find the type of event and its extra information
                String eventType = "";
                String extraInfo = "";

                if (event instanceof AcademicEvent) {
                    eventType = "Academic";
                    extraInfo = ((AcademicEvent) event).getSubject();

                } else if (event instanceof ClubEvent) {
                    eventType = "Club";
                    extraInfo = ((ClubEvent) event).getClubName();

                } else if (event instanceof CareerEvent) {
                    eventType = "Career";
                    extraInfo = ((CareerEvent) event).getCompanyName();

                } else if (event instanceof SocialEvent) {
                    eventType = "Social";
                    extraInfo = ((SocialEvent) event).getActivityType();

                } else {
                    System.out.println("Unknown event type. Skipping event.");
                    continue;
                }

                // Write event information to the file
                writer.write(eventType + ","
                        + event.getEventId() + ","
                        + event.getName() + ","
                        + event.getDescription() + ","
                        + event.getDate() + ","
                        + event.getTime() + ","
                        + event.getLocation() + ","
                        + event.getCapacity() + ","
                        + extraInfo + "\n");
            }

            System.out.println("Events saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving events: " + e.getMessage());
        }
    }



    // Load events from a text file
    public ArrayList<Event> loadEvents(String filename) {

        ArrayList<Event> events = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            String line;

            // Read one event at a time
            while ((line = reader.readLine()) != null) {

                // Separate the event information
                String[] data = line.split(",", -1);

                // Each event should have 9 fields
                if (data.length != 9) {
                    throw new IllegalArgumentException("Invalid event record in " + filename);
                }

                try {
                    String eventType = data[0];
                    int eventId = Integer.parseInt(data[1]);
                    String name = data[2];
                    String description = data[3];
                    LocalDate date = LocalDate.parse(data[4]);
                    LocalTime time = LocalTime.parse(data[5]);
                    String location = data[6];
                    int capacity = Integer.parseInt(data[7]);
                    String extraInfo = data[8];

                    Event event;

                    // Recreate the correct event subclass
                    switch (eventType) {

                        case "Academic":
                            event = new AcademicEvent(
                                eventId, name, description,
                                date, time, location, capacity, extraInfo
                            );
                            break;

                        case "Club":
                            event = new ClubEvent(
                                eventId, name, description,
                                date, time, location, capacity, extraInfo
                            );
                            break;

                        case "Career":
                            event = new CareerEvent(
                                eventId, name, description,
                                date, time, location, capacity, extraInfo
                            );
                            break;

                        case "Social":
                            event = new SocialEvent(
                                eventId, name, description,
                                date, time, location, capacity, extraInfo
                            );
                            break;

                        default:
                            throw new IllegalArgumentException("Unknown event type: " + eventType);
                    }

                    // Add the event to the list
                    events.add(event);

                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Invalid event in " + filename, e);
                }
            }

            System.out.println("Events loaded successfully.");

        } catch (IOException e) {
            throw new IllegalStateException("Error loading events: " + filename, e);
        }

        return events;
    }



    // Save registrations to a text file
    public void saveRegistrations(ArrayList<Registration> registrations,
                                  String filename) {

        try (FileWriter writer = new FileWriter(filename)) {

            // Go through each registration
            for (Registration registration : registrations) {

                // Get the student and event IDs
                int studentId = registration.getStudent().getStudentId();
                int eventId = registration.getEvent().getEventId();

                // Save the registration information
                writer.write(studentId + ","
                        + eventId + ","
                        + registration.getRegistrationDate() + ","
                        + registration.getRegistrationStatus() + "\n");
            }

            System.out.println("Registrations saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving registrations: " + e.getMessage());
        }
    }


    // Load registrations from a text file
    public ArrayList<Registration> loadRegistrations(
            String filename,
            ArrayList<Student> students,
            ArrayList<Event> events) {

        ArrayList<Registration> registrations = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            String line;

            // Read each registration from the file
            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",", -1);

                // Each registration must have 4 fields
                if (data.length != 4) {
                    throw new IllegalArgumentException("Invalid registration record in " + filename);
                }

                try {
                    int studentId = Integer.parseInt(data[0]);
                    int eventId = Integer.parseInt(data[1]);
                    LocalDateTime date = LocalDateTime.parse(data[2]);
                    String status = data[3];

                    Student foundStudent = null;
                    Event foundEvent = null;

                    // Find the student by ID
                    for (Student student : students) {
                        if (student.getStudentId() == studentId) {
                            foundStudent = student;
                            break;
                        }
                    }

                    // Find the event by ID
                    for (Event event : events) {
                        if (event.getEventId() == eventId) {
                            foundEvent = event;
                            break;
                        }
                    }

                    // Skip registrations with missing students or events
                    if (foundStudent == null || foundEvent == null) {
                        throw new IllegalArgumentException("Registration references a missing student or event in " + filename);
                    }

                    // Restore the original date and status
                    Registration registration = new Registration(
                        foundStudent, foundEvent, date, status
                    );

                    registrations.add(registration);

                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Invalid registration in " + filename, e);
                }
            }

            System.out.println("Registrations loaded successfully.");

        } catch (IOException e) {
            throw new IllegalStateException("Error loading registrations: " + filename, e);
        }

        return registrations;
    }


    // Save organizers and their event assignments.
    public void saveOrganizers(
            java.util.ArrayList<model.Organizer> organizers,
            String filename) {

        try (java.io.FileWriter writer =
                new java.io.FileWriter(filename)) {

            for (model.Organizer organizer : organizers) {
                java.util.ArrayList<Integer> eventIds =
                        organizer.getEventIds();

                // An organizer with no events still gets saved.
                if (eventIds.isEmpty()) {
                    writer.write(organizer.getOrganizerId() + ","
                            + organizer.getName() + ","
                            + organizer.getEmail() + ",\n");
                } else {
                    for (int eventId : eventIds) {
                        writer.write(organizer.getOrganizerId() + ","
                                + organizer.getName() + ","
                                + organizer.getEmail() + ","
                                + eventId + "\n");
                    }
                }
            }

            System.out.println("Organizers saved successfully.");

        } catch (java.io.IOException e) {
            throw new IllegalStateException(
                    "Error saving organizers: " + filename, e);
        }
    }

    // Load organizers and their event assignments.
    public java.util.ArrayList<model.Organizer> loadOrganizers(
            String filename) {

        java.util.LinkedHashMap<Integer, model.Organizer> organizers =
                new java.util.LinkedHashMap<>();

        try (java.io.BufferedReader reader =
                new java.io.BufferedReader(
                        new java.io.FileReader(filename))) {

            String line;

            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }

                String[] data = line.split(",", -1);

                if (data.length != 4) {
                    throw new IllegalArgumentException(
                            "Invalid organizer record in " + filename);
                }

                try {
                    int organizerId = Integer.parseInt(data[0]);
                    String name = data[1];
                    String email = data[2];

                    model.Organizer organizer =
                            organizers.get(organizerId);

                    if (organizer == null) {
                        organizer = new model.Organizer(
                                organizerId, name, email);
                        organizers.put(organizerId, organizer);
                    } else if (!organizer.getName().equals(name)
                            || !organizer.getEmail().equals(email)) {
                        throw new IllegalArgumentException(
                                "Conflicting organizer details for ID "
                                        + organizerId);
                    }

                    if (!data[3].isEmpty()) {
                        organizer.addEvent(
                                Integer.parseInt(data[3]));
                    }

                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(
                            "Invalid organizer or event ID in "
                                    + filename, e);
                }
            }

            System.out.println("Organizers loaded successfully.");

        } catch (java.io.IOException e) {
            throw new IllegalStateException(
                    "Error loading organizers: " + filename, e);
        }

        return new java.util.ArrayList<>(organizers.values());
    }

}
