package SpaceAssignmentSystem;

public class Booking {
	public Date start;
	public Date end;
	public String owner;

	public Booking(Date start, Date end, String s) {
		this.start = start;
		this.end = end;
		owner = s;
	}
	
	public String toString() {
		return String.format("Start date: %s\nEnd date: %s\nOwner: %s", start.toString(), end.toString(), owner);
	}
}
