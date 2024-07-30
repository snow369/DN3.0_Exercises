import java.util.ArrayList;
import java.util.List;

public class ObserverPattern {

    // Define the Subject Interface
    interface Stock {
        void registerObserver(Observer observer);
        void deregisterObserver(Observer observer);
        void notifyObservers();
    }

    // Implement Concrete Subject
    static class StockMarket implements Stock {
        private List<Observer> observers;
        private String stockName;
        private double stockPrice;

        public StockMarket(String stockName) {
            this.stockName = stockName;
            this.observers = new ArrayList<>();
        }

        public void setStockPrice(double price) {
            this.stockPrice = price;
            notifyObservers();
        }

        @Override
        public void registerObserver(Observer observer) {
            observers.add(observer);
        }

        @Override
        public void deregisterObserver(Observer observer) {
            observers.remove(observer);
        }

        @Override
        public void notifyObservers() {
            for (Observer observer : observers) {
                observer.update(stockName, stockPrice);
            }
        }
    }

    // Define the Observer Interface
    interface Observer {
        void update(String stockName, double stockPrice);
    }

    // Implement Concrete Observers
    static class MobileApp implements Observer {
        @Override
        public void update(String stockName, double stockPrice) {
            System.out.println("MobileApp - Stock: " + stockName + ", New Price: $" + stockPrice);
        }
    }

    static class WebApp implements Observer {
        @Override
        public void update(String stockName, double stockPrice) {
            System.out.println("WebApp - Stock: " + stockName + ", New Price: $" + stockPrice);
        }
    }

    
    public static void main(String[] args) {
        // Create a StockMarket instance
        StockMarket stockMarket = new StockMarket("TechCorp");

        // Create observer instances
        Observer mobileApp = new MobileApp();
        Observer webApp = new WebApp();

        // Register observers with the StockMarket
        stockMarket.registerObserver(mobileApp);
        stockMarket.registerObserver(webApp);

        // Simulate a change in stock price
        System.out.println("Updating stock price...");
        stockMarket.setStockPrice(150.75);

        // Deregister an observer
        stockMarket.deregisterObserver(mobileApp);

        // Simulate another change in stock price
        System.out.println("Updating stock price...");
        stockMarket.setStockPrice(155.00);
    }
}

//OUTPUT
// Updating stock price...
// MobileApp - Stock: TechCorp, New Price: $150.75
// WebApp - Stock: TechCorp, New Price: $150.75
// Updating stock price...
// WebApp - Stock: TechCorp, New Price: $155.0