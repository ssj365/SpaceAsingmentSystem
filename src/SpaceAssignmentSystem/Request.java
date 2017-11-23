package SpaceAssignmentSystem;

public class Request {
	public String room;
	public Booking booking;
	
	public Request(Date start, Date end, String owner, String r) {
		booking = new Booking(start, end, owner);
		room = r;
	}
	
}
