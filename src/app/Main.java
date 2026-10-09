package app;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.time.format.DateTimeParseException;

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
                    input.nextLine(); // Clear input
                    continue;   // Skip to next loop iteration
                }

                // Else, assign menuSelect to input
                menuSelect = input.nextInt();
                input.nextLine();   // Clear input

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
                    System.out.println("Add Student selected.\n");

                    int studentIdBeingAdded = 0;
                    // Begin a loop to get a valid student ID input from the user.
                    while (true) {
                        // Prompt user to enter a student ID
                        System.out.print("Enter a 4-digit student ID: ");

                        // If the user inputs a non-integer, handle it
                        if (!input.hasNextInt()) {
                            System.out.println("Invalid input. Please enter an integer.");
                            input.nextLine();   // Clear input
                            continue;   // Loop and prompt again
                        }

                        // Otherwise, assign the input
                        studentIdBeingAdded = input.nextInt();
                        input.nextLine();   // Clear input

                        // If the input is not a digit anywhere from 1000 to 9999, continue loop
                        if (studentIdBeingAdded < 1000 || studentIdBeingAdded > 9999) {
                            System.out.println("Invalid input. ID must be a valid 4-digit number.");
                            continue;   // Loop and prompt again
                        }
                        // Else, check if the entered ID is a duplicate
                        if (studentManager.findStudentById(studentIdBeingAdded) != null) {
                            System.out.println("That student ID is already in use.");
                            continue;   // If so, loop and prompt again
                        }
                        break;  // Otherwise, successfully exit the student ID input loop
                    }

                    // Prompt user to enter name
                    System.out.print("Enter the student's name: ");
                    String studentNameBeingAdded = input.nextLine();

                    // Prompt user to enter email
                    System.out.print("Enter the student's email: ");
                    String studentEmailBeingAdded = input.nextLine();

                    // Prompt user to enter major
                    System.out.print("Enter the student's major: ");
                    String studentMajorBeingAdded = input.nextLine();

                    // Given the provided input from the user, create the student
                    Student studentBeingAdded = new Student (studentIdBeingAdded, studentNameBeingAdded,
                                                             studentEmailBeingAdded, studentMajorBeingAdded);

                    // Now that the student is created, add them to the student array.
                    if (studentManager.addStudent(studentBeingAdded)) {
                        System.out.println("Student added successfully.\n");
                    }
                    else {
                        System.out.println("Failed to add student.\n");
                    }
                    break;
                }

                // 2. View Students
                case 2: {
                    System.out.println("View Students selected.\n");

                    System.out.println("Student List\n");

                    studentManager.displayAllStudents();
                    System.out.println();   // Print newline for spacing
                    break;
                }

                // 3. Create Event
                case 3: {
                    System.out.println("Create Event selected.\n");

                    int eventIdBeingAdded = 0;
                    // Begin a loop to get a valid event ID input from the user.
                    while (true) {
                        // Prompt user to enter an event ID
                        System.out.print("Enter a 4-digit event ID: ");

                        // If the user inputs a non-integer, handle it
                        if (!input.hasNextInt()) {
                            System.out.println("Invalid input. Please enter an integer.");
                            input.nextLine();   // Clear input
                            continue;   // Loop and prompt again
                        }

                        // Otherwise, assign the input
                        eventIdBeingAdded = input.nextInt();
                        input.nextLine();   // Clear input

                        // If the input is not a digit anywhere from 1000 to 9999, handle it
                        if (eventIdBeingAdded < 1000 || eventIdBeingAdded > 9999) {
                            System.out.println("Invalid input. ID must be a valid 4-digit number.");
                            continue;   // Loop and prompt again
                        }
                        // Else, check if the entered ID is a duplicate
                        if (eventSystem.findEventById(eventIdBeingAdded) != null) {
                            System.out.println("That event ID is already in use.");
                            continue;   // If so, loop and prompt again
                        }
                        break;  // Otherwise, successfully exit the event ID input loop
                    }

                    // Prompt user to enter name
                    System.out.print("Enter the event's name: ");
                    String eventNameBeingAdded = input.nextLine();

                    // Prompt user to enter description
                    System.out.print("Enter the event's description: ");
                    String eventDescriptionBeingAdded = input.nextLine();

                    // Initialize date object before entering loop
                    LocalDate eventDateBeingAdded;
                    // Enter a loop to get a valid input for the event date
                    while (true) {
                        // Prompt user to enter date
                        System.out.print("Enter the event's date (YYYY-MM-DD): ");
                        // Try parsing and assigning it
                        try {
                            eventDateBeingAdded = LocalDate.parse(input.nextLine());
                            break;  // If success, exit while loop
                        } catch (DateTimeParseException ex) {
                            // Otherwise, inform user and continue loop.
                            System.out.println("Invalid date. Please use YYYY-MM-DD.");
                        }
                    }

                    // Initialize time object before entering loop
                    LocalTime eventTimeBeingAdded;
                    // Enter a loop to get a valid input for the event time
                    while (true) {
                        // Prompt user to enter time
                        System.out.print("Enter the event's time (HH:MM): ");
                        // Try parsing and assigning it
                        try {
                            eventTimeBeingAdded = LocalTime.parse(input.nextLine());
                            break;  // If success, exit while loop
                        } catch (DateTimeParseException ex) {
                            // Otherwise, inform user and continue loop.
                            System.out.println("Invalid time. Please use HH:MM.");
                        }
                    }

                    // Prompt user to enter location
                    System.out.print("Enter the event's location: ");
                    String eventLocationBeingAdded = input.nextLine();

                    // Initialize capacity
                    int eventCapacityBeingAdded = 0;
                    while (true) {
                        // Prompt user to enter capacity
                        System.out.print("Enter the event's capacity: ");

                        // If the user inputs a non-integer, handle it
                        if (!input.hasNextInt()) {
                            System.out.println("Invalid input. Please enter an integer.");
                            input.nextLine();   // Clear input
                            continue;   // Loop and prompt again
                        }

                        // Otherwise, assign the input
                        eventCapacityBeingAdded = input.nextInt();
                        input.nextLine();   // Clear input

                        // If the user inputs a number less than 1, handle it
                        if (eventCapacityBeingAdded < 1) {
                            System.out.println("Invalid input. Event must have at least 1 capacity.");
                            continue;   // Loop and prompt again
                        }

                        break;  // Else, exit while loop for capacity input
                    }

                    System.out.println();   // Print newline for spacing

                    int eventTypeBeingAdded = 0;
                    // Begin a loop to get a valid event type from the user.
                    while (true) {
                        // Prompt user to enter an event type
                        System.out.print("Enter an event type (1: Academic, 2: Career, 3: Club, or 4: Social): ");
                        
                        // If the user inputs a non-integer, handle it
                        if (!input.hasNextInt()) {
                            System.out.println("Invalid input. Please enter an integer.");
                            input.nextLine();   // Clear input
                            continue;   // Loop and prompt again
                        }

                        // Otherwise, assign the input
                        eventTypeBeingAdded = input.nextInt();
                        input.nextLine();   // Clear input

                        // If the input does not match any of the given types, continue loop
                        if (eventTypeBeingAdded < 1 || eventTypeBeingAdded > 4) {
                            System.out.println("Invalid input. Please enter one of the specified types.");
                            continue;   // Skip to next loop iteration
                        }
                        break;  // Else, exit the event type input loop
                    }

                    // Initialize the event object being added before entering the switch statement.
                    Event eventBeingAdded;
                    // Depending on the event type the user input, the last field of the event differs.
                    switch (eventTypeBeingAdded) {
                        case 1: {
                            System.out.println("Academic event selected.\n");

                            // Prompt user for subject
                            System.out.print("Enter the event's subject: ");
                            String eventSubjectBeingAdded = input.nextLine();

                            // Create the academic event
                            eventBeingAdded = new AcademicEvent(eventIdBeingAdded, eventNameBeingAdded,
                                                                      eventDescriptionBeingAdded, eventDateBeingAdded,
                                                                      eventTimeBeingAdded, eventLocationBeingAdded,
                                                                      eventCapacityBeingAdded, eventSubjectBeingAdded);                         
                            break;
                        }
                        case 2: {
                            System.out.println("Career event selected.\n");

                            // Prompt user for company name
                            System.out.print("Enter the event's company name: ");
                            String eventCompNameBeingAdded = input.nextLine();

                            // Create the career event
                            eventBeingAdded = new CareerEvent(eventIdBeingAdded, eventNameBeingAdded,
                                                                    eventDescriptionBeingAdded, eventDateBeingAdded,
                                                                    eventTimeBeingAdded, eventLocationBeingAdded,
                                                                    eventCapacityBeingAdded, eventCompNameBeingAdded);
                            break;
                        }
                        case 3: {
                            System.out.println("Club event selected.\n");

                            // Prompt user for club name
                            System.out.print("Enter the event's club name: ");
                            String eventClubNameBeingAdded = input.nextLine();

                            // Create the club event
                            eventBeingAdded = new ClubEvent(eventIdBeingAdded, eventNameBeingAdded,
                                                                  eventDescriptionBeingAdded, eventDateBeingAdded,
                                                                  eventTimeBeingAdded, eventLocationBeingAdded,
                                                                  eventCapacityBeingAdded, eventClubNameBeingAdded);
                            break;
                        }
                        case 4: {
                            System.out.println("Social event selected.\n");

                            // Prompt user for activity
                            System.out.print("Enter the event's activity type: ");
                            String eventActivityBeingAdded = input.nextLine();

                            // Create the social event
                            eventBeingAdded = new SocialEvent(eventIdBeingAdded, eventNameBeingAdded,
                                                                    eventDescriptionBeingAdded, eventDateBeingAdded,
                                                                    eventTimeBeingAdded, eventLocationBeingAdded,
                                                                    eventCapacityBeingAdded, eventActivityBeingAdded);
                            break;
                        }
                        default: {
                            throw new IllegalStateException("Invalid event type.");
                        }
                    }

                    // Now that the event is created, add it to the event array.
                    if (eventSystem.addEvent(eventBeingAdded)) {
                        System.out.println("Event added successfully.\n");
                    }
                    else {
                        System.out.println("Failed to add event.\n");
                    }
                    break;
                }

                // 4. View Events
                case 4: {
                    System.out.println("View Events selected.\n");

                    System.out.println("Event List\n");

                    eventSystem.displayAllEvents();
                    System.out.println();   // Print newline for spacing
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

                    int registerStudentId = 0;
                    // Enter a loop to get a valid student ID from the user
                    while (true) {
                        // Prompt user to enter a student ID
                        System.out.print("Enter student ID: ");

                        // If the user inputs a non-integer, handle it
                        if (!input.hasNextInt()) {
                            System.out.println("Invalid input. Please enter an integer.");
                            input.nextLine();   // Clear input
                            continue;   // Loop and prompt again
                        }

                        // Otherwise, assign the input
                        registerStudentId = input.nextInt();
                        input.nextLine();   // Clear input

                        // If the input is not a digit anywhere from 1000 to 9999, handle it
                        if (registerStudentId < 1000 || registerStudentId > 9999) {
                            System.out.println("Invalid input. ID must be a valid 4-digit number.");
                            continue;   // Loop and prompt again
                        }
                        break;  // Else, successfully exit loop for student ID input
                    }

                    // Check if the entered student ID exists
                    Student registerStudent = studentManager.findStudentById(registerStudentId);
                    // If not, inform user and immediately return to menu
                    if (registerStudent == null) {
                        System.out.println("Student not found.\n");
                        break;
                    }

                    int registerEventId = 0;
                    // Enter a loop to get a valid event ID from the user
                    while (true) {
                        // Prompt user to enter event ID
                        System.out.print("Enter event ID: ");

                        // If the user inputs a non-integer, handle it
                        if (!input.hasNextInt()) {
                            System.out.println("Invalid input. Please enter an integer.");
                            input.nextLine();   // Clear input
                            continue;   // Loop and prompt again
                        }
                    
                        // Otherwise, assign the input
                        registerEventId = input.nextInt();
                        input.nextLine();   // Clear 
                        
                        // If the input is not a digit anywhere from 1000 to 9999, handle it
                        if (registerEventId < 1000 || registerEventId > 9999) {
                            System.out.println("Invalid input. ID must be a valid 4-digit number.");
                            continue;   // Loop and prompt again
                        }
                        break;  // Else, successfully exit loop for event ID input
                    }

                    // Check if the entered event ID exists
                    Event registerEvent = eventSystem.findEventById(registerEventId);
                    // If not, inform user and immediately return to menu
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

                    // Initialize before entering loop
                    int registrationStudentId = 0;
                    // Enter a loop to get a valid student ID from the user
                    while (true) {
                        // Prompt user to enter student ID
                        System.out.print("Enter student ID: ");

                        // If the user inputs a non-integer, handle it
                        if (!input.hasNextInt()) {
                            System.out.println("Invalid input. Please enter an integer.");
                            input.nextLine();   // Clear input
                            continue;   // Loop and prompt again
                        }
                    
                        // Otherwise, assign the input
                        registrationStudentId = input.nextInt();
                        input.nextLine();   // Clear 
                        
                        // If the input is not a digit anywhere from 1000 to 9999, handle it
                        if (registrationStudentId < 1000 || registrationStudentId > 9999) {
                            System.out.println("Invalid input. ID must be a valid 4-digit number.");
                            continue;   // Loop and prompt again
                        }
                        break;  // Else, successfully exit loop for student ID input
                    }

                    // Using the given ID, search for the student
                    Student registrationStudent = studentManager.findStudentById(registrationStudentId);
                    // If the specified student doesn't exist, inform user and immediately return to menu
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

                    // Initialize before entering loop
                    int registrationEventId = 0;
                    // Enter a loop to get a valid event ID from the user
                    while (true) {
                        // Prompt user to enter event ID
                        System.out.print("Enter event ID: ");

                        // If the user inputs a non-integer, handle it
                        if (!input.hasNextInt()) {
                            System.out.println("Invalid input. Please enter an integer.");
                            input.nextLine();   // Clear input
                            continue;   // Loop and prompt again
                        }
                    
                        // Otherwise, assign the input
                        registrationEventId = input.nextInt();
                        input.nextLine();   // Clear 
                        
                        // If the input is not a digit anywhere from 1000 to 9999, handle it
                        if (registrationEventId < 1000 || registrationEventId > 9999) {
                            System.out.println("Invalid input. ID must be a valid 4-digit number.");
                            continue;   // Loop and prompt again
                        }
                        break;  // Else, successfully exit loop for event ID input
                    }

                    // Using the given ID, search for the event
                    Event registrationEvent = eventSystem.findEventById(registrationEventId);
                    // If the specified event doesn't exist, inform user and immediately return to menu
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