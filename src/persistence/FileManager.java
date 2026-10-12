
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

import model.Registration;

public class FileManager {

    // Saves a list of students to a text file
    public void saveStudents(ArrayList<Student> students, String filename) {

        try (FileWriter writer = new FileWriter(filename)) {

            for (Student student : students) {
                writer.write(student.getStudentId() + ","
                        + student.getName() + ","
                        + student.getEmail() + ","
                        + student.getMajor() + "\n");
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
                    System.out.println("Skipping invalid student record.");
                    continue;
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
                    System.out.println("Skipping student with invalid ID.");
                }
            }

            System.out.println("Students loaded successfully.");

        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
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
                    System.out.println("Skipping invalid event record.");
                    continue;
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
                            System.out.println("Unknown event type: " + eventType);
                            continue;
                    }

                    // Add the event to the list
                    events.add(event);

                } catch (IllegalArgumentException e) {
                    System.out.println("Skipping invalid event: " + e.getMessage());
                }
            }

            System.out.println("Events loaded successfully.");

        } catch (IOException e) {
            System.out.println("Error loading events: " + e.getMessage());
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



}
