import java.time.LocalDate;
import java.time.LocalTime;

public class SocialEvent extends Event {

    private String activityType;

    public SocialEvent(int eventId, String name, String description,
                       LocalDate date, LocalTime time,
                       String location, int capacity,
                       String activityType) {

        super(eventId, name, description, date, time, location, capacity);
        this.activityType = activityType;
    }

    public String getActivityType() {
        return activityType;
    }

    @Override
    public void displayDetails() {
        System.out.println("Social Event");
        System.out.println("ID: " + getEventId());
        System.out.println("Name: " + getName());
        System.out.println("Description: " + getDescription());
        System.out.println("Date: " + getDate());
        System.out.println("Time: " + getTime());
        System.out.println("Location: " + getLocation());
        System.out.println("Capacity: " + getCapacity());
        System.out.println("Activity Type: " + activityType);
    }
}