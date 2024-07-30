
public class Order {

    // Attributes
    String orderId;
    String customerName;
    double totalPrice;

    // Constructor
    public Order(String orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    // Bubble Sort to sort orders by totalPrice
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].totalPrice > orders[j + 1].totalPrice) {
                    // Swap orders[j] and orders[j + 1]
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    // Quick Sort to sort orders by totalPrice
    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);

            quickSort(orders, low, pi - 1);  // Before pi
            quickSort(orders, pi + 1, high); // After pi
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].totalPrice;
        int i = (low - 1); // Index of smaller element

        for (int j = low; j < high; j++) {
            if (orders[j].totalPrice <= pivot) {
                i++;

                // Swap orders[i] and orders[j]
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }

        // Swap orders[i + 1] and orders[high] (or pivot)
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;

        return i + 1;
    }

    // Main method to demonstrate usage
    public static void main(String[] args) {
        // Create some orders
        Order[] orders = {
            new Order("O003", "Alice", 250.75),
            new Order("O001", "Bob", 125.50),
            new Order("O002", "Charlie", 300.00)
        };

        // Print unsorted orders
        System.out.println("Unsorted Orders:");
        for (Order order : orders) {
            System.out.println("OrderID: " + order.orderId + ", Customer: " + order.customerName + ", Total Price: " + order.totalPrice);
        }

        // Bubble Sort
        bubbleSort(orders);
        System.out.println("\nOrders Sorted by Bubble Sort:");
        for (Order order : orders) {
            System.out.println("OrderID: " + order.orderId + ", Customer: " + order.customerName + ", Total Price: " + order.totalPrice);
        }

        // Re-create orders for Quick Sort demonstration
        orders = new Order[]{
            new Order("O003", "Alice", 250.75),
            new Order("O001", "Bob", 125.50),
            new Order("O002", "Charlie", 300.00)
        };

        // Quick Sort
        quickSort(orders, 0, orders.length - 1);
        System.out.println("\nOrders Sorted by Quick Sort:");
        for (Order order : orders) {
            System.out.println("OrderID: " + order.orderId + ", Customer: " + order.customerName + ", Total Price: " + order.totalPrice);
        }
    }
}

//OUTPUT
// Unsorted Orders:
// OrderID: O003, Customer: Alice, Total Price: 250.75
// OrderID: O001, Customer: Bob, Total Price: 125.5
// OrderID: O002, Customer: Charlie, Total Price: 300.0

// Orders Sorted by Bubble Sort:
// OrderID: O001, Customer: Bob, Total Price: 125.5
// OrderID: O003, Customer: Alice, Total Price: 250.75
// OrderID: O002, Customer: Charlie, Total Price: 300.0

// Orders Sorted by Quick Sort:
// OrderID: O001, Customer: Bob, Total Price: 125.5
// OrderID: O003, Customer: Alice, Total Price: 250.75
// OrderID: O002, Customer: Charlie, Total Price: 300.0