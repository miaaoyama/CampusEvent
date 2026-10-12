
package persistence;

import java.util.ArrayList;

import model.Student;
import model.AcademicEvent;
import model.Event;
import model.Registration;

import service.StudentManager;
import service.EventSystem;
import service.RegistrationSystem;

import model.AcademicEvent;
import java.time.LocalDate;
import java.time.LocalTime;


public class ManagerIntegrationTest {

    public static void main(String[] args) {

        // Creates the management systems
        StudentManager studentManager = new StudentManager();
        EventSystem eventSystem = new EventSystem();
        RegistrationSystem registrationSystem = new RegistrationSystem();

        System.out.println("Management systems created successfully!");


        // Adds a student to StudentManager
        Student student = new Student(101, "John", "john@email.com", "Computer Science");
        studentManager.addStudent(student);

        // Tests the getAllStudents() method
        ArrayList<Student> students = studentManager.getAllStudents();

        System.out.println("Number of students: " + students.size());

        // Check that StudentManager returned the correct student
        if (students.size() != 1 || students.get(0) != student) {
            throw new AssertionError("Student retrieval test failed!");
        }

        System.out.println("Student retrieval test passed!");


        // Add an event to EventSystem
        Event event = new AcademicEvent(
            201,
            "Java Workshop",
            "Learn Java",
            LocalDate.of(2026, 10, 15),
            LocalTime.of(14, 0),
            "Room 201",
            30,
            "Computer Science"
        );

        eventSystem.addEvent(event);


        // Gets all events from EventSystem
        ArrayList<Event> events = eventSystem.getAllEvents();

        // Displays the number of events
        System.out.println("Number of events: " + events.size());

        // Verify that the correct event was returned
        if (events.size() != 1 || events.get(0) != event) {
            throw new AssertionError("Event retrieval test failed!");
        }

        System.out.println("Event retrieval test passed!");

        // Create an existing registration
        Registration registration = new Registration(
            student,
            event,
            java.time.LocalDateTime.of(2026, 10, 11, 12, 0),
            "Registered"
        );

        // Restore the registration into RegistrationSystem
        registrationSystem.restoreRegistration(registration);

        // Retrieve all registrations
        ArrayList<Registration> registrations =
            registrationSystem.getAllRegistrations();

        // Verifies the correct registration was restored
        if (registrations.size() != 1 ||
            registrations.get(0) != registration) {
            throw new AssertionError("Registration restoration test failed!");
        }

        System.out.println("Registration restoration test passed!");

        // Creates a FileManager object
        FileManager fileManager = new FileManager();

        // Saves data from all three management systems
        fileManager.saveStudents(studentManager.getAllStudents(), "manager_students_test.csv");

        fileManager.saveEvents(eventSystem.getAllEvents(), "manager_events_test.csv");

        fileManager.saveRegistrations(registrationSystem.getAllRegistrations(),"manager_registrations_test.csv");

        System.out.println("Manager data saving test completed!");


        // Loads  the saved data from CSV files
        ArrayList<Student> loadedStudents = fileManager.loadStudents("manager_students_test.csv");

        ArrayList<Event> loadedEvents = fileManager.loadEvents("manager_events_test.csv");

        ArrayList<Registration> loadedRegistrations = fileManager.loadRegistrations("manager_registrations_test.csv", loadedStudents, loadedEvents);

        // Verify the loaded data
        if (loadedStudents.size() != 1 || loadedEvents.size() != 1 || loadedRegistrations.size() != 1) {
            throw new AssertionError("Manager data loading test failed!");
        }

        System.out.println("Manager data loading test passed!");

        // Simulates restarting the application
        StudentManager newStudentManager = new StudentManager();
        EventSystem newEventSystem = new EventSystem();
        RegistrationSystem newRegistrationSystem = new RegistrationSystem();

        // Restores students
        for (Student s : loadedStudents) {
            newStudentManager.addStudent(s);
        }

        // Restores events
        for (Event e : loadedEvents) {
            newEventSystem.addEvent(e);
        }

        // Restores registrations
        for (Registration r : loadedRegistrations) {
            newRegistrationSystem.restoreRegistration(r);
        }

        // Verifies all data was restored
        if (newStudentManager.getAllStudents().size() != 1 || newEventSystem.getAllEvents().size() != 1 || newRegistrationSystem.getAllRegistrations().size() != 1) {
            throw new AssertionError("Manager restoration test failed!");
        }

        System.out.println("Manager restoration test passed!");

    }
}
