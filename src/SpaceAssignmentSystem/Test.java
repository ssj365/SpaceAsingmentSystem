package SpaceAssignmentSystem;

import java.util.Map;

public class Test {
	public static void main(String[] args) throws SchedulerException {
		Scheduler s = new Scheduler();
		RequestHandler r = s.createRequestHandler();
		Date start = new Date(1, 1, 12, 0);
		Date end = new Date(1, 1, 13, 0);
		r.approveRequest(new Request(start, end, "null", "room1"));
		Map<String, Booking[]> m = r.getFullDay(new Date(1, 1));
		Booking[] bs = m.get("room1");
		for(Booking b : bs) {
			System.out.println(b.toString());
		}
	}

}
