package SpaceAssignmentSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class RequestHandler {
	private Scheduler schedule;
	private ArrayList<Request> pending;
	private Queue<Request> denied;
	
	public RequestHandler(Scheduler s) {
		schedule = s;
		pending = new ArrayList<Request>();
		denied = new ArrayBlockingQueue<Request>(30);
	}
	
	public Map<String, Booking[]> getFullDay(Date d) {
		Map<String, Booking[]> m = new HashMap<String, Booking[]>();
		for(String s : schedule.roomNames()) {
			ArrayList<Booking> bookings = new ArrayList<Booking>();
			for(Booking b : schedule.getRoom(s).bookings) {
				if(b.end.month == d.month && b.end.day == d.day) {
					bookings.add(b);
				}
			}
			m.put(s, (Booking[]) bookings.toArray());
		}
		return m;
	}
	
	public Map<String, Booking[]> getNoConflict(Date d) {
		Map<String, Booking[]> m = getFullDay(d);
		ArrayList<Booking> noConflict = new ArrayList<Booking>();
		for(String s : m.keySet() ) {
			Booking[] conflict = m.get(s);
			noConflict.clear();
			for(Booking b1 : conflict) {
				for(Booking b2 : noConflict) {
					if( b1.start.after(b2.start) && b1.start.before(b2.end) ) {
						noConflict.remove(b2);
					}
					if( b1.end.after(b2.start) && b1.end.before(b2.end) ) {
						noConflict.remove(b2);
					}
					else {
						noConflict.add(b1);
					}
				}
			}
			m.put(s, (Booking[]) noConflict.toArray());
		}
		return m;
	}
	
	public Map<String, Booking[]> getConflict(Date d){
		Map<String, Booking[]> m = getFullDay(d);
		ArrayList<Booking> noConflict = new ArrayList<Booking>();
		for(String s : m.keySet() ) {
			ArrayList<Booking> conflict = new ArrayList<Booking>();
			for(Booking b : m.get(s)) {
				conflict.add(b);
			}
			noConflict.clear();
			for(Booking b1 : conflict) {
				for(Booking b2 : noConflict) {
					if( b1.start.after(b2.start) && b1.start.before(b2.end) ) {
						noConflict.remove(b2);
					}
					if( b1.end.after(b2.start) && b1.end.before(b2.end) ) {
						noConflict.remove(b2);
					}
					else {
						noConflict.add(b1);
					}
				}
			}
			conflict.removeAll(noConflict);
			m.put(s, (Booking[]) conflict.toArray());
		}
		return m;	
	}
	
	public void newRequest(Request r) {
		pending.add(r);
	}
	
	public void denyRequest(Request r) {
		pending.remove(r);
		denied.add(r);
	}
	
	public boolean approveRequest(Request r) throws SchedulerException {
		Room room = schedule.getRoom(r.room);
		for(Booking b : room.bookings) {
			if( b.start.after(r.booking.start) && b.start.before(r.booking.end) ) {
				throw new SchedulerException();
			}
			if( b.end.after(r.booking.start) && b.end.before(r.booking.end) ) {
				throw new SchedulerException();
			}
			else{
				return room.bookings.add(r.booking);
			}
		}
		return false;				
	}
	
	public void rejectRequest() {}
	
	
}
