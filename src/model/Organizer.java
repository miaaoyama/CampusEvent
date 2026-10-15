package model;

public class Organizer {
	private int OragnizerId;
	private String name;
	private String email;
	private String Association;
    private ArrayList<Integer> eventIds;

	public Organizer(int OragnizerId, String name, String email, String Association) 
    {
		this.OragnizerId = OragnizerId;
		this.name = name;
		this.email = email;
		this.Association = Association;
        this.eventIds = new ArrayList<>();
	}

	public int getOragnizerId() 
    {
		return OragnizerId;
	}

	public String getName() 
    {
		return name;
	}

	public String getEmail() 
    {
		return email;
	}

	public String getAssociation() 
    {
		return Association;
	}

    public ArrayList<Integer> getEventIds() 
    {
        return new ArrayList<>(eventIds);
    }

    public boolean addEvent(int eventId) 
    {
        if (eventIds.contains(eventId)) 
        {
            return false;
        }

        eventIds.add(eventId);
        return true;

	}
}