import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

public class App {
	Boolean loggedIn;
	Database db;
	User currentUser;
	Scanner sc;
	Cart cart;

	public static void main(String[] args) {
		App app = new App();

		app.init();
			
		for(;;) {
			if(!app.loggedIn) app.auth();
			else {
				app.cmd();
			}
		}
	}

	void cmd() {
		System.out.println("add_product | delete_product | list | add_to_cart | checkout | logout");
		String cmd = this.sc.next(); 
		switch(cmd) {
			case "add_product":
				//TODO : Restrict except admin.
				System.out.println(("Entered add_product.")); 
				this.addProduct();
				break;
			case "delete_product": 
				//TODO : Restrict except admin.
				System.out.println(("Entered delete_product."));
				break;
			case "list": 
				System.out.println(("Entered list."));
				this.listProducts();	
				break;
			case "add_to_cart": 
				System.out.println(("Entered add_to_cart."));
				addToCart();
				break;
			case "checkout": 
				System.out.println(("Entered checkout."));	
				checkout();
				break;
			case "logout": 
				System.out.println(("Entered logout."));	
				logout();
				break;
		}
	}

	public void init() {
		this.loggedIn = false;
		this.db = new Database();
		this.sc = new Scanner(System.in);
		this.cart = new Cart();
	}

	public void auth() {
		System.out.print("(login/signup): ");	
		String res = sc.next();	
		switch(res) {
			case "login": 
				this.login();
				break;
			case "signup": 
				this.signup();
				this.login();
				break;
		}
	}
	
	public void login() {
		String un, pw;
		for(;;) {
			System.out.print("username: ");
			un = sc.next();
			System.out.print("password: ");
			pw = sc.next();
			User currentUser = this.db.checkLogin(un, pw);
			if(currentUser != null) { this.loggedIn = true; this.currentUser = currentUser; return; }
			else {
				System.out.println("Invalid username or password.");
			}
		}	
	}

	public void signup() {
		String name, address, dob, un, pw;
		Date dob_date;

		for(;;) {
			System.out.println("name: ");
			name = sc.next();
			System.out.println("address");
			address = sc.next();
			System.out.println("date of birth (dd/mm/yyyy): ");
			dob = sc.next();
			//TODO Set util function for date parsing.
			try {
				SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
				dob_date = date_format.parse(dob);
			}
			catch(ParseException e) {
				System.out.println("Cannot process date.");
				continue;
			}
			System.out.println("username: ");
			un = sc.next();
			System.out.println("password: ");
			pw = sc.next();
			System.out.println("confirm password: ");
			if(!sc.next().equals(pw)) { System.out.println("password doesn't match."); continue; }
			else { this.db.addUser(name, un, address, dob_date, pw); return; }
		}
	}

	public void listProducts() {
		Product[] products = this.db.listProducts();
		System.out.format("%-15s%-15s%-15s%-15s%n", "Product ID", "Name", "Price", "Description");
		for (Product p: products){
			System.out.format("%-15s%-15s%-15s%-15s%n", p.product_id, p.name, p.price, p.description);
		}
	}

	public void addProduct() {
		System.out.print("Enter product ID: ");
		String p_id = sc.next();
		System.out.print("Enter name: ");
		String p_name = sc.next();
		System.out.println("Enter price: ");
		Double p_price = sc.nextDouble();
		System.out.print("Enter description: ");
		String p_desc = sc.nextLine();
		this.db.addProduct(p_id, p_name, p_price, p_desc);
	}

	public void deleteProduct() {
		System.out.print("Enter product ID:");
		String p_id = sc.next();
		this.db.deleteProduct(p_id);
	}

	public void addToCart() {
		System.out.print("Enter product ID: ");
		String p_id = sc.next();
		Product p = this.db.fetchProduct(p_id);
		if(p_id != null) cart.addItem(p);
	}

	public void checkout() {
		System.out.println("Checkout: " + cart.checkout()); 
	}

	public void logout() {
		this.loggedIn = false;
	}
}
