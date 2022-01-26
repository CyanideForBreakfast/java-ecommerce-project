import java.util.ArrayList;

public class Cart {
	ArrayList<Product> items;	

	public Cart() {
		this.items = new ArrayList<Product>();
	}

	public void addItem(Product p) {
		items.add(p);
	}

	public Double checkout() {
		Double total = 0.0;
		for(Product p: this.items) {
			total += p.price;
		}
		return total;
	}
}
