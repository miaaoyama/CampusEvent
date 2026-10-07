package app;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int menuSelect = 1;

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
                    System.out.println("WIP - Register Student for Event selected\n");
                    break;
                }

                // 7. Cancel Registration
                case 7: {
                    System.out.println("WIP - Cancel Registration selected\n");
                    break;
                }

                // 8. View Student Registrations
                case 8: {
                    System.out.println("WIP - View Student Registrations selected\n");
                    break;
                }

                // 9. View Event Attendees
                case 9: {
                    System.out.println("WIP - View Event Attendees selected\n");
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