import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Database {
	HashMap<String, String> authData;
	HashMap<String, User> users;
	HashMap<String, Product> products;

	public Database()	{
		this.authData = new HashMap<String, String>();
		this.users = new HashMap<String, User>();
		this.products = new HashMap<String, Product>();

		this.init();
	}

	// Adds dummy data.
	void init() {
		Date dob_date;
		try {
			SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
			dob_date = date_format.parse("29/09/2010");
		}
		catch(ParseException e) {
			System.out.println("Cannot process date.");
			return;
		}
		User user1 = new User("USER 1", "user1", "somewhere someplace", dob_date, false);
		this.authData.put("user1", "password1");
		this.users.put("user1", user1);
		
		Product p = new Product("12198", "some product", "it does something", 12.00);
		this.products.put("12198", p);
	}

	User checkLogin(String username, String password) {
		if(this.authData.get(username).equals(password)) { return users.get(username); };
		return null;
	}
	
	void addUser(String name, String username, String address, Date dob, String password) {
		authData.put(username, password);
		users.put(username, new User(name, username, address, dob, false));
	}

	Product[] listProducts() {
		Product[] ret = new Product[this.products.size()];

		int i = 0;
		for(Product p: this.products.values()) {
			ret[i++] = p;
		}

		return ret;
	}

	public void addProduct(String product_id, String name, Double price, String description) {
		Product p = new Product(product_id, name, description, price);
		this.products.put(product_id, p);
	}

	public void deleteProduct(String product_id) {
		this.products.remove(product_id);
	}
	
	public Product fetchProduct(String product_id) {
		return this.products.get(product_id);
	}
}
