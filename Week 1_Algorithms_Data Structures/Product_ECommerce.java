import java.util.Arrays;

public class Product_ECommerce {
    // Attributes
    String productId;
    String productName;
    String category;

    // Constructor
    public Product_ECommerce(String productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    // Linear Search
    public static Product_ECommerce linearSearch(Product_ECommerce[] products, String targetId) {
        for (Product_ECommerce product : products) {
            if (product.productId.equals(targetId)) {
                return product;
            }
        }
        return null; // Not found
    }

    // Binary Search
    public static Product_ECommerce binarySearch(Product_ECommerce[] products, String targetId) {
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = products[mid].productId.compareTo(targetId);

            if (cmp == 0) {
                return products[mid];
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null; // Not found
    }
    
    public static void main(String[] args) {
        // Create some products
        Product_ECommerce[] products = {
            new Product_ECommerce("P003", "Tablet", "Electronics"),
            new Product_ECommerce("P001", "Laptop", "Electronics"),
            new Product_ECommerce("P002", "Smartphone", "Electronics")
        };

        // Linear Search
        String searchId = "P002";
        Product_ECommerce foundProductLinear = linearSearch(products, searchId);
        if (foundProductLinear != null) {
            System.out.println("Linear Search Found: " + foundProductLinear.productName);
        } else {
            System.out.println("Linear Search: Product not found.");
        }

        // Binary Search (requires sorted array)
        Arrays.sort(products, (a, b) -> a.productId.compareTo(b.productId)); // Sort by productId

        Product_ECommerce foundProductBinary = binarySearch(products, searchId);
        if (foundProductBinary != null) {
            System.out.println("Binary Search Found: " + foundProductBinary.productName);
        } else {
            System.out.println("Binary Search: Product not found.");
        }
    }
}

//OUTPUT
// Linear Search Found: Smartphone
// Binary Search Found: Smartphone