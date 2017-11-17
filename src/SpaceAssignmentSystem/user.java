package SpaceAssignmentSystem;

public class user {
	static String user = "Admin";
	static String [] userList = {"Admin", "User 1", "User 2", "User 3", "User 4", "User 5"};
	public static void setUser(String selectedUser) {
		 	user  = selectedUser;
	}
	public static String getUser() {
		return user;
	}
	
	public static String[] getUsers() {
		return userList;
	}
}
		 
