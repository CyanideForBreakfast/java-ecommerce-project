import java.util.Date;

public class User {
	String name;
	String username;
	String address;
	Date dob;	
	Boolean admin;

	public User(String name, String username, String address, Date dob, Boolean admin) {
		this.name = name;
		this.username = username;
		this.address = address;
		this.dob = dob;
		this.admin = admin;
	}
}
