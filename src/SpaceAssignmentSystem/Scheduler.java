package SpaceAssignmentSystem;

import java.io.*;

public class Scheduler {
	private Room[] rooms;
	public String[] names = {"room1", "room2", "room3", "room4", "room5"};
	
	public Scheduler(){ 
		rooms = new Room[5];
		for (String s : names) {
			System.out.println(s);
			build(s);
		}
	}
	
	public boolean addBooking(Request r) {
		int i = pick(r.room);
		return rooms[i].addBooking(r.booking);
	}
	
	private void build(String s) {
		int i = pick(s);
		System.out.println(i);
		try {
			FileInputStream fileIn = new FileInputStream(s + ".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			rooms[i] = (Room) in.readObject();
			fileIn.close();
			in.close();
		}
		catch(Exception e) {
			rooms[i] = new Room();
		}
	}
	
	private boolean blackOut(Request r) {
		return false;
		//to do
	}
	
	private int pick(String s) {
		if( s.equals("room1") ) {
			return 0;
		}
		else if( s.equals("room2") ) {
			return 1;
		}
		else if( s.equals("room3") ) {
			return 2;
		}
		else if( s.equals("room4") ) {
			return 3;
		}
		else if( s.equals("room5") ) {
			return 4;
		}
		else{
			return -1;			
		}
	}
}
