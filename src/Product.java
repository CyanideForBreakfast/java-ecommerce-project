public class Product {
    public String name;
    public String product_id;
    public String description;
    public Double price;

    public Product(String product_id, String name, String description, Double price) {
        this.name = name;
        this.product_id = product_id;
        this.description = description;
        this.price = price;
    }
}
