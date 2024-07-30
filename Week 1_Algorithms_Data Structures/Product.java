import java.util.HashMap;

public class Product {
    String productId;
    String productName;
    int quantity;
    double price;

    // Static HashMap to store products
    private static HashMap<String, Product> inventory = new HashMap<>();

    // Constructor
    public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    // Method to add a product to inventory
    public static void addProduct(Product product) {
        if (inventory.containsKey(product.productId)) {
            System.out.println("Product with ID " + product.productId + " already exists.");
        } else {
            inventory.put(product.productId, product);
            System.out.println("Product added: " + product.productId + " " + product.productName);
        }
    }

    // Method to update a product in inventory
    public static void updateProduct(Product product) {
        if (inventory.containsKey(product.productId)) {
            inventory.put(product.productId, product);
            System.out.println("Product updated: " + product.productId + " " + product.productName);
        } else {
            System.out.println("Product with ID " + product.productId + " not found.");
        }
    }

    // Method to delete a product from inventory
    public static void deleteProduct(String productId) {
        if (inventory.containsKey(productId)) {
            Product removedProduct = inventory.remove(productId);
            System.out.println("Product removed: " + removedProduct.productId + " " + removedProduct.productName);
        } else {
            System.out.println("Product with ID " + productId + " not found.");
        }
    }

    // Method to get a product from inventory
    public static Product getProduct(String productId) {
        return inventory.get(productId);
    }

    public static void main(String[] args) {
        // Creating some products
        Product product1 = new Product("P001", "Laptop", 10, 999.99);
        Product product2 = new Product("P002", "Smartphone", 20, 499.99);

        // Add products to inventory
        Product.addProduct(product1);
        Product.addProduct(product2);

        // Update a product
        Product updatedProduct1 = new Product("P001", "Laptop", 15, 999.99);
        Product.updateProduct(updatedProduct1);

        // Delete a product
        Product.deleteProduct("P002");

        // Get a product
        Product retrievedProduct = Product.getProduct("P001");
        System.out.println("Retrieved Product: " + retrievedProduct.productId + " " + retrievedProduct.productName);
    }
}
