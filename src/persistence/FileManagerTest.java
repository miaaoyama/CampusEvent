
package persistence;

import java.util.ArrayList;
import model.Student;

import java.time.LocalDate;
import java.time.LocalTime;
import model.Event;
import model.AcademicEvent;
import model.ClubEvent;
import model.CareerEvent;
import model.SocialEvent;

import model.Registration;

public class FileManagerTest {

    public static void main(String[] args) {

        // Create two students
        Student student1 = new Student(
            101, "John", "john@email.com", "Computer Science"
        );

        Student student2 = new Student(
            102, "Maria", "maria@email.com", "Mathematics"
        );

        // Create a list of students
        ArrayList<Student> students = new ArrayList<>();

        students.add(student1);
        students.add(student2);

        // Create FileManager object
        FileManager fileManager = new FileManager();

        // Save students to a file
        fileManager.saveStudents(students, "students.txt");


        // Load students from the saved file
        ArrayList<Student> loadedStudents =
                fileManager.loadStudents("students.txt");

        // Display the loaded students
        System.out.println("\nLoaded Students:");

        for (Student student : loadedStudents) {
            System.out.println(student);
        }




        // Creates a list of events
        ArrayList<Event> events = new ArrayList<>();

        // Create an academic event
        events.add(new AcademicEvent(
            201, "Java Workshop", "Learn Java",
            LocalDate.of(2026, 10, 15),
            LocalTime.of(14, 0),
            "Room 201", 30, "Computer Science"
        ));

        // Create a club event
        events.add(new ClubEvent(
            202, "Coding Club", "Weekly meeting",
            LocalDate.of(2026, 10, 16),
            LocalTime.of(15, 0),
            "Room 105", 25, "Programming Club"
        ));

        // Create a career event
        events.add(new CareerEvent(
            203, "Career Fair", "Meet employers",
            LocalDate.of(2026, 10, 17),
            LocalTime.of(10, 0),
            "Main Hall", 100, "Google"
        ));

        // Create a social event
        events.add(new SocialEvent(
            204, "Campus Picnic", "Meet students",
            LocalDate.of(2026, 10, 18),
            LocalTime.of(12, 0),
            "Campus Park", 50, "Outdoor Activity"
        ));

        // Save events to a text file
        fileManager.saveEvents(events, "events.txt");



        // Load events from the saved file
        ArrayList<Event> loadedEvents =
                fileManager.loadEvents("events.txt");

        // Display all loaded events
        System.out.println("\nLoaded Events:");

        for (Event event : loadedEvents) {
            event.displayDetails();
            System.out.println("--------------------");
        }


        // Create a list of registrations
        ArrayList<Registration> registrations = new ArrayList<>();

        // Register student 101 for event 201
        Registration registration1 =
                new Registration(students.get(0), events.get(0));

        // Register student 102 for event 202
        Registration registration2 =
                new Registration(students.get(1), events.get(1));

        // Cancel the second registration
        registration2.cancelRegistration();

        // Add both registrations to the list
        registrations.add(registration1);
        registrations.add(registration2);

        // Save registrations to a file
        fileManager.saveRegistrations(registrations, "registrations.txt");


    }
}
