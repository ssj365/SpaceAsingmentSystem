package SpaceAssignmentSystem;

import java.io.*;

public class Scheduler {
	private Room[] rooms;
	private String[] names = {"room1", "room2", "room3", "room4", "room5"};
	
	public Scheduler(){ 
		rooms = new Room[names.length];
		for (String s : names) {
			build(s);
		}
	}
	
	public Scheduler(String[] nms){ 
		names = nms;
		rooms = new Room[names.length];
		for (String s : names) {
			build(s);
		}
	}
	
	public RequestHandler createRequestHandler() {
		return new RequestHandler(this);
	}
	
	public Room getRoom(String s) { 
		return rooms[pick(s)];
	}
	
	public String[] roomNames() {
		return names;
	}
	
	private void build(String s) {
		int i = pick(s);
		try {
			FileInputStream fileIn = new FileInputStream(s + ".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			rooms[i] = (Room) in.readObject();
			fileIn.close();
			in.close();
		}
		catch(Exception e) {
			rooms[i] = new Room(s);
		}
	}
	
	private int pick(String s1) {
		int i = 0;
		for(String s2 : names) {
			if( s1.equals(s2) ) {
				return i;
			}
			else {
				i++;
			}
		}
		return -1;
	}
}
