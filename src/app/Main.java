package app;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import model.Student;
import model.Event;
import model.AcademicEvent;
import model.CareerEvent;
import model.ClubEvent;
import model.SocialEvent;
import model.Registration;
import service.StudentManager;
import service.EventSystem;
import service.RegistrationSystem;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Initialize management objects
        StudentManager studentManager = new StudentManager();
        EventSystem eventSystem = new EventSystem();
        RegistrationSystem registrationSystem = new RegistrationSystem();

        int menuSelect = 1;

        // **TEMPORARY TEST DATA**
        Student testStudent1 = new Student(1001, "Test Student1", "test1@example.com", "Computer Science");
        Student testStudent2 = new Student(1002, "Test Student2", "test2@example.com", "Computer Science");
        Student testStudent3 = new Student(1003, "Test Student3", "test3@example.com", "Computer Science");
        Event testEvent1 = new AcademicEvent(2001, "Test Workshop", "A test academic event", LocalDate.of(2026, 10, 10), LocalTime.of(10, 0), "Room 101", 2, "Academics");
        Event testEvent2 = new ClubEvent(2002, "Test Hangout", "A test club event", LocalDate.of(2026, 11, 14), LocalTime.of(14, 30), "Room 201", 1, "Stuff");
        studentManager.addStudent(testStudent1);
        studentManager.addStudent(testStudent2);
        studentManager.addStudent(testStudent3);
        eventSystem.addEvent(testEvent1);
        eventSystem.addEvent(testEvent2);
        // **TEMPORARY TEST DATA**

        // Begin a loop to return to menu after each selection (until the selection is 0)
        do {
            // Print menu title
            System.out.println("Campus Event Management System\n");

            // Print menu
            System.out.print("1. Add Student\n" + "2. View Students\n" + "3. Create Event\n" + "4. View Events\n"
                            + "5. Search Events\n" + "6. Register Student for Event\n" + "7. Cancel Registration\n"
                            + "8. View Student Registrations\n" + "9. View Event Attendees\n"
                            + "10. View Organizers\n" + "11. System Reports\n" + "0. Exit\n\n");

            // Begin a while loop to get valid user input
            while (true) {
                System.out.print("Enter selection: ");

                // If user does not input an int
                if (!input.hasNextInt()) {
                    System.out.println("Invalid input. Please enter an integer.");
                    input.nextLine(); // Remove invalid input
                    continue;   // Skip to next loop iteration
                }

                // Else, assign menuSelect to input
                menuSelect = input.nextInt();
                input.nextLine();   // Remove newline after the int

                // If the value of menuSelect is not a valid int
                if (menuSelect < 0 || menuSelect > 11) {
                    System.out.println("Invalid input. Please enter a number from 0 to 11.");
                    continue; // Skip to next loop iteration
                }

                // Else, exit the menu selection while loop
                break;
            }

            // Perform the action designated by the user's selection
            switch (menuSelect) {
                // 1. Add Student
                case 1: {
                    System.out.println("WIP - Add Student selected\n");
                    break;
                }

                // 2. View Students
                case 2: {
                    System.out.println("WIP - View Students selected\n");
                    break;
                }

                // 3. Create Event
                case 3: {
                    System.out.println("WIP - Create Event selected\n");
                    break;
                }

                // 4. View Events
                case 4: {
                    System.out.println("WIP - View Events selected\n");
                    break;
                }

                // 5. Search Events
                case 5: {
                    System.out.println("WIP - Search Events selected\n");
                    break;
                }

                // 6. Register Student for Event
                case 6: {
                    System.out.println("Register Student for Event Selected.\n");

                    // Prompt user to enter a student ID
                    System.out.print("Enter student ID: ");
                    int registerStudentId = input.nextInt();
                    // Check if the entered student ID exists
                    Student registerStudent = studentManager.findStudentById(registerStudentId);
                    // If not, inform user and return to menu
                    if (registerStudent == null) {
                        System.out.println("Student not found.\n");
                        break;
                    }

                    // Else, continue by prompting user to enter event ID
                    System.out.print("Enter event ID: ");
                    int registerEventId = input.nextInt();
                    // Check if the entered event ID exists
                    Event registerEvent = eventSystem.findEventById(registerEventId);
                    // If not, inform user and return to menu
                    if (registerEvent == null) {
                        System.out.println("Event not found.\n");
                        break;
                    }

                    // Else, the IDs are valid, so now call addRegistration.
                    // Further checks will be done within this method, returning true or false.
                    if (registrationSystem.addRegistration(registerStudent, registerEvent)) {
                        System.out.println("Registration successful.\n");
                    }
                    else {
                        System.out.println("Registration failed. Student may already be registered or the event may be full.\n");
                    }
                    break;
                }

                // 7. Cancel Registration
                case 7: {
                    System.out.println("WIP - Cancel Registration selected\n");
                    break;
                }

                // 8. View Student Registrations
                case 8: {
                    System.out.println("View Student Registrations selected.\n");

                    // Prompt the user for a student's ID
                    System.out.print("Enter student ID: ");
                    int registrationStudentId = input.nextInt();

                    // Using the given ID, search for the student
                    Student registrationStudent = studentManager.findStudentById(registrationStudentId);

                    // If the specified student doesn't exist, inform user and return to menu
                    if (registrationStudent == null) {
                        System.out.println("Student not found.\n");
                        break;
                    }

                    // Else, call method to display all of the specified student's registered events
                    registrationSystem.viewStudentRegistrations(registrationStudent);

                    System.out.println();   // Print newline for spacing
                    break;
                }

                // 9. View Event Attendees
                case 9: {
                    System.out.println("View Event Attendees selected.\n");

                    // Prompt the user for an event's ID
                    System.out.print("Enter event ID: ");
                    int registrationEventId = input.nextInt();

                    // Using the given ID, search for the event
                    Event registrationEvent = eventSystem.findEventById(registrationEventId);

                    // If the specified event doesn't exist, inform user and return to menu
                    if (registrationEvent == null) {
                        System.out.println("Event not found.\n");
                        break;
                    }

                    // Else, call method to display all of the specified event's registered students
                    registrationSystem.viewEventAttendees(registrationEvent);
                    
                    System.out.println();   // Print newline for spacing
                    break;
                }

                // 10. View Organizers
                case 10: {
                    System.out.println("WIP - View Organizers selected\n");
                    break;
                }

                // 11. System Reports
                case 11: {
                    System.out.println("WIP - System Reports selected\n");
                    break;
                }
            }

        } while (menuSelect != 0);

        System.out.println("Exiting program.");
    }
}