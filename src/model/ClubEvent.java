import java.time.LocalDate;
import java.time.LocalTime;

public class ClubEvent extends Event {

    private String clubName;

    public ClubEvent(int eventId, String name, String description,
                     LocalDate date, LocalTime time,
                     String location, int capacity,
                     String clubName) {

        super(eventId, name, description, date, time, location, capacity);
        this.clubName = clubName;
    }

    public String getClubName() {
        return clubName;
    }

    @Override
    public void displayDetails() {
        System.out.println("Club Event");
        System.out.println("ID: " + getEventId());
        System.out.println("Name: " + getName());
        System.out.println("Description: " + getDescription());
        System.out.println("Date: " + getDate());
        System.out.println("Time: " + getTime());
        System.out.println("Location: " + getLocation());
        System.out.println("Capacity: " + getCapacity());
        System.out.println("Club: " + clubName);
    }
}