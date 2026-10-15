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
import model.Organizer;
import model.SocialEvent;
import model.Registration;
import service.StudentManager;
import service.EventSystem;
import service.RegistrationSystem;
import service.OrganizerManager;
import service.ReportSystem;
import persistence.FileManager;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Initialize management objects
        StudentManager studentManager = new StudentManager();
        EventSystem eventSystem = new EventSystem();
        RegistrationSystem registrationSystem = new RegistrationSystem();
        OrganizerManager organizerManager = new OrganizerManager();

        // Initialize file management object for saving and loading data
        FileManager fileManager = new FileManager();

        // Initialize an array of the four data file names, in the data folder
        String[] dataFiles = {
            "data/students.csv",
            "data/events.csv",
            "data/registrations.csv",
            "data/organizers.csv"
        };


        // Begin a loop for each file, to check if each one exists
        for (String filename : dataFiles) {
            // Initialize a path representing the file location
            Path path = Path.of(filename);

            // If the file exists, but isnt a regular file or cant be read, exit the program immediately
            if (Files.exists(path) && (!Files.isRegularFile(path) || !Files.isReadable(path))) {

                System.out.println("Cannot safely read " + filename + ". Exiting without saving.");

                input.close();  // Close the Scanner object
                return; // Exit the program
            }
        }

        // Try to load the saved data of each file
        try {

            // If students.csv does not exist, start with empty list
            if (Files.notExists(Path.of("data/students.csv"))) {
                System.out.println("No saved students found. Starting empty.");
            } else {    // Else, file exists, so load the data from students.csv into the students array
                for (Student student : fileManager.loadStudents("data/students.csv")) {
                    studentManager.addStudent(student);
                }
            }

            // If events.csv does not exist, start with empty list
            if (Files.notExists(Path.of("data/events.csv"))) {
                System.out.println("No saved events found. Starting empty.");
            } else {    // Else, file exists, so load the data from events.csv into the events array
                for (Event event : fileManager.loadEvents("data/events.csv")) {
                    eventSystem.addEvent(event);
                }
            }

            // If registrations.csv does not exist, start with empty list
            if (Files.notExists(Path.of("data/registrations.csv"))) {
                System.out.println("No saved registrations found. Starting empty.");
            } else {    // Else, file exists, so load the data from registrations.csv into the registrations array
                for (Registration registration : fileManager.loadRegistrations("data/registrations.csv", studentManager.getAllStudents(), eventSystem.getAllEvents())) {
                    registrationSystem.restoreRegistration(registration);
                }
            }

            // If organizers.csv does not exist, start with empty list
            if (Files.notExists(Path.of("data/organizers.csv"))) {
                System.out.println("No saved organizers found. Starting empty.");
            } else {    // Else, file exists, so load the data from organizers.csv into the organizers array
                for (model.Organizer organizer :
                        fileManager.loadOrganizers("data/organizers.csv")) {
                    if (!organizerManager.addOrganizer(organizer)) {    // If a duplicate organizer is found when loading, handle it
                        throw new IllegalArgumentException(
                                "Duplicate organizer ID: "
                                + organizer.getOrganizerId());
                    }
                }
            }
          
          System.out.println();   // Print newline for spacing

        } catch (IllegalArgumentException | IllegalStateException e) {

            System.out.println("Error loading saved data: " + e.getMessage());
            System.out.println("Program stopped to prevent overwriting saved data.");

            input.close();  // Close the Scanner object
            return; // Exit the program
        }


        // Initialize an int to hold the user's menu selections
        int menuSelect = 1;

        // Begin a loop to return to menu after each selection (until the selection is 0)
        do {
            // Print menu title
            System.out.println("Campus Event Management System\n");

            // Print menu
            System.out.print("1. Add Student\n" + "2. View Students\n" + "3. Create Event\n" + "4. View Events\n"
                            + "5. Search Events\n" + "6. Register Student for Event\n" + "7. Cancel Registration\n"
                            + "8. View Student Registrations\n" + "9. View Event Attendees\n" + "10. Add Organizer\n"
                            + "11. View Organizers\n" + "12. System Reports\n" + "13. Find organizer\n" + "14. Search Associatons\n" 
                            + "0. Exit\n\n");

            // User input check (using helper function readInt() at the bottom of main)
            menuSelect = readInt(input, "Enter selection: ", 0, 12);

            // Perform the action designated by the user's selection
            switch (menuSelect) {
                // 1. Add Student
                case 1: {
                    System.out.println("Add Student selected.\n");

                    // User input student ID
                    int studentIdBeingAdded = readInt(input, "Enter student ID: ", 1000, 9999);
                    
                    // Check if the entered ID is a duplicate
                    if (studentManager.findStudentById(studentIdBeingAdded) != null) {
                        System.out.println("That student ID is already in use.\n");
                        break;   // If so, inform user and immediately return to menu
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

                    // User input event ID
                    int eventIdBeingAdded = readInt(input, "Enter event ID: ", 1000, 9999);
                    
                    // Check if the entered ID is a duplicate
                    if (eventSystem.findEventById(eventIdBeingAdded) != null) {
                        System.out.println("That event ID is already in use.\n");
                        break;   // If so, inform user and immediately return to menu
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

                    // User input check (using helper function readInt() at the bottom of main)
                    int eventCapacityBeingAdded = readInt(input, "Enter event capacity: ", 1, 100);

                    System.out.println();   // Print newline for spacing

                    // User input check (using helper function readInt() at the bottom of main)
                    int eventTypeBeingAdded = readInt(input, "Enter an event type (1: Academic, 2: Career, 3: Club, or 4: Social): ", 1, 4);

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

                    System.out.println("\n=== Search Events ===");
                    System.out.println("1. Academic Events");
                    System.out.println("2. Club Events");
                    System.out.println("3. Social Events");
                    System.out.println("4. Career Events");
                    System.out.println("0. Return to Main Menu");

                    int searchChoice = readInt(
                            input, "Select event type: ", 0, 4);

                    if (searchChoice == 0) {
                        break;
                    }

                    String[] types = {
                        "academic", "club", "social", "career"
                    };

                    String selectedType = types[searchChoice - 1];

                    java.util.ArrayList<model.Event> matches =
                            eventSystem.searchEventsByType(selectedType);

                    if (matches.isEmpty()) {
                        System.out.println(
                            "No " + selectedType + " events found."
                        );
                    } else {
                        System.out.println(
                            "\nMatching " + selectedType + " events:"
                        );

                        for (model.Event event : matches) {
                            event.displayDetails();
                            System.out.println("--------------------");
                        }
                    }

                    break;
                }

                // 6. Register Student for Event
                case 6: {
                    System.out.println("Register Student for Event Selected.\n");

                    // User input student ID
                    int registerStudentId = readInt(input, "Enter student ID: ", 1000, 9999);

                    // Check if the student exists
                    Student registerStudent = studentManager.findStudentById(registerStudentId);
                    if (registerStudent == null) {
                        System.out.println("Student not found.\n");
                        break;  // If not, inform user and immediately return to menu
                    }

                    // User input event ID
                    int registerEventId = readInt(input, "Enter event ID: ", 1000, 9999);

                    // Check if the event exists
                    Event registerEvent = eventSystem.findEventById(registerEventId);
                    if (registerEvent == null) {
                        System.out.println("Event not found.\n");
                        break;  // If not, inform user and immediately return to menu
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
                    System.out.println("Cancel Registration selected\n");

                    // User input student ID
                    int studentId = readInt(input, "Enter 4-digit student ID: ", 1000, 9999);

                    // Check if the student exists
                    Student student = studentManager.findStudentById(studentId);
                    if (student == null) {
                        System.out.println("Student not found.\n");
                        break;  // If not, inform user and immediately return to menu
                    }

                    // User input event ID
                    int eventId =   readInt(input, "Enter 4-digit event ID: ", 1000, 9999 );

                    // Check if the event exists
                    Event event = eventSystem.findEventById(eventId);
                    if (event == null) {
                        System.out.println("Event not found.\n");
                        break;  // If not, inform user and immediately return to menu
                    }

                    Registration cancelled = registrationSystem.cancelRegistration(studentId, eventId);

                    if (cancelled != null) {
                        System.out.println("Registration cancelled for "
                                + cancelled.getStudent().getName() + " --> "
                                + cancelled.getEvent().getName() + ".\n");
                    } else {
                        System.out.println("No active registration found for that student and event.\n");
                    }
                    break;
                }


                // 8. View Student Registrations
                case 8: {
                    System.out.println("View Student Registrations selected.\n");

                    // User input student ID
                    int registrationStudentId = readInt(input, "Enter student ID: ", 1000, 9999);

                    // Check if the student exists
                    Student registrationStudent = studentManager.findStudentById(registrationStudentId);
                    if (registrationStudent == null) {
                        System.out.println("Student not found.\n");
                        break;  // If not, inform user and immediately return to menu
                    }

                    // Else, display all of the student's registered events
                    registrationSystem.viewStudentRegistrations(registrationStudent);

                    System.out.println();   // Print newline for spacing
                    break;
                }

                // 9. View Event Attendees
                case 9: {
                    System.out.println("View Event Attendees selected.\n");

                    // User input event ID
                    int registrationEventId = readInt(input, "Enter event ID: ", 1000, 9999);

                    // Check if the event exists
                    Event registrationEvent = eventSystem.findEventById(registrationEventId);
                    if (registrationEvent == null) {
                        System.out.println("Event not found.\n");
                        break;  // If not, inform user and immediately return to menu
                    }

                    // Else, display all of the event's registered students
                    registrationSystem.viewEventAttendees(registrationEvent);
                    
                    System.out.println();   // Print newline for spacing
                    break;
                }

                // 10. Add Organizer
                case 10: {
                    System.out.println("Add Organizer selected.\n");

                    // User input organizer ID
                    int organizerIdBeingAdded = readInt(input, "Enter organizer ID: ", 1000, 9999);
                    
                    // Check if the entered ID is a duplicate
                    if (organizerManager.findOrganizerById(organizerIdBeingAdded) != null) {
                        System.out.println("That organizer ID is already in use.\n");
                        break;   // If so, inform user and immediately return to menu
                    }

                    // Prompt user to enter name
                    System.out.print("Enter the organizer's name: ");
                    String organizerNameBeingAdded = input.nextLine();

                    // Prompt user to enter email
                    System.out.print("Enter the organizer's email: ");
                    String organizerEmailBeingAdded = input.nextLine();

                    // Prompt user to enter assossiation
                    System.out.print("Enter the organizer's assossiation: ");
                    String organizerAssossiationBeingAdded = input.nextLine();

                    // Create the organizer with an empty list of event IDs
                    Organizer organizerBeingAdded = new Organizer(organizerIdBeingAdded,
                                                                  organizerNameBeingAdded,
                                                                  organizerEmailBeingAdded,
                                                                  organizerAssossiationBeingAdded);

                    System.out.println();   // Print newline for spacing

                    boolean addAnotherEvent = true;

                    // Enter a loop to assign event(s) to the organizer
                    while (addAnotherEvent) {
                        // User input event ID
                        int organizerEventIdBeingAdded = readInt(input,"Enter the ID of the event being organized: ",1000, 9999);

                        // Check if the event exists
                        Event organizationEvent = eventSystem.findEventById(organizerEventIdBeingAdded);
                        if (organizationEvent == null) {
                            System.out.println("Event not found. Please try again.");
                            continue;  // If not, inform user and loop
                        }

                        // Add the event to the organizer
                        if (organizerBeingAdded.addEvent(organizerEventIdBeingAdded)) {
                            System.out.println("Event assigned successfully.\n");
                        }
                        else {
                            System.out.println("This organizer is already assigned to that event.\n");
                        }

                        // Ask whether or not the user wants to add another event
                        while (true) {
                            System.out.print("Add another event for this organizer? (y/n): ");
                            String addOrganizerYNString = input.nextLine().trim();

                            if (addOrganizerYNString.equalsIgnoreCase("y")) {
                                System.out.println();   // Print newline for spacing
                                break;  // If yes, exit this loop then continue adding another event
                            }
                            else if (addOrganizerYNString.equalsIgnoreCase("n")) {
                                addAnotherEvent = false;
                                break;  // If no, exit both loops
                            }
                            else {  // If the user didnt input a valid response, loop
                                System.out.println("Invalid input. Please enter y or n.");
                            }
                        }
                    }

                    // Add the completed organizer to the organizer manager
                    if (organizerManager.addOrganizer(organizerBeingAdded)) {
                        System.out.println("Organizer added successfully.\n");
                    }
                    else {
                        System.out.println("Failed to add organizer.\n");
                    }

                    break;
                }

                // 11. View Organizers
                case 11: {
                    System.out.println("=== View Organizers ===");
                    organizerManager.displayAllOrganizers(eventSystem);
                    System.out.println();
                    break;
                }

                // 12. System Reports
                case 12: {
                    ReportSystem reports = new ReportSystem(
                            eventSystem, registrationSystem);

                    int reportChoice;

                    do {
                        System.out.println("\n=== System Reports ===");
                        System.out.println("1. Events with Available Capacity");
                        System.out.println("2. Full Events");
                        System.out.println("3. Most Popular Events");
                        System.out.println("4. Students Registered for Multiple Events");
                        System.out.println("0. Return to Main Menu");

                        reportChoice = readInt(input, "Select a report: ", 0, 4);

                        switch (reportChoice) {
                            case 1:
                                reports.displayAvailableEvents();
                                break;
                            case 2:
                                reports.displayFullEvents();
                                break;
                            case 3:
                                reports.displayPopularEvents();
                                break;
                            case 4:
                                reports.displayStudentsWithMultipleEvents();
                                break;
                            case 0:
                                System.out.println("Returning to main menu.\n");
                                break;
                            default:
                                System.out.println("Invalid report option.");
                        }

                    } while (reportChoice != 0);


                    break;
                }

                case 13: {
                    System.out.println("Find organizer by their ID.\n");

                    // User input organizer ID
                    int OragnizerId = readInt(input, "Enter organizer ID: ", 1000, 9999);

                    // Check if the organizer exists
                    Organizer OrganizerFound = OrganizerManager.findOrganizerById(OrganizerId);
                    if (registrationStudent == null) 
                    {
                        System.out.println("Student not found.\n");
                        break;  // If not, inform user and immediately return to menu
                    }

                    // Else, display the organizer
                    System.out.println(OrganizerFound);

                    System.out.println();   // Print newline for spacing
                    break;
                }

                case 14: {
                    System.out.println("Find organizers by their department or organization.\n");

                    //prompt for Association name
                    System.out.print("Enter the department or organization's name: ");
                    String SeekAssociation = input.nextLine();

                    findOrganizersByAssociation find = new findOrganizersByAssociation();

                    String[] result = OrganizerManager.findOrganizersByAssociation();

                    for (String names : result) 
                    {
                        System.out.println(name);
                    }
                }
            }

        // When the user enters 0, the menu loop is exited.
        } while (menuSelect != 0);

        System.out.println();   // Print newline for spacing

        // Save the contents of the students, events, registrations and organizers
        // arrays in their respective files before exiting the program.
        fileManager.saveStudents(studentManager.getAllStudents(), "data/students.csv");

        fileManager.saveEvents(eventSystem.getAllEvents(), "data/events.csv");

        fileManager.saveRegistrations(registrationSystem.getAllRegistrations(),"data/registrations.csv");

        fileManager.saveOrganizers(organizerManager.getAllOrganizers(), "data/organizers.csv");

        System.out.println("Exiting program.");

        input.close();  // Close the Scanner object
    }


    // MAIN HELPER METHODS

    // readInt - verifies and returns an int input. Also allows a custom prompt, min range, and max range
    private static int readInt(
        Scanner input, String prompt, int min, int max) {

        while (true) {
            System.out.print(prompt);

            if (!input.hasNextInt()) {
                System.out.println("Please enter a valid integer value.");
                input.next(); 
                continue;
            }

            int value = input.nextInt();

            if (value >= min && value <= max) {
                input.nextLine(); 
                return value;
            }

            System.out.println("Enter a number from " + min + " to " + max + ".");
        }
    }
}
