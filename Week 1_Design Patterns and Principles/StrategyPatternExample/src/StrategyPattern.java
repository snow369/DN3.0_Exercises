public class StrategyPattern {

    // Define the Strategy Interface
    interface PaymentStrategy {
        void pay(double amount);
    }

    // Implement Concrete Strategies
    static class CreditCardPayment implements PaymentStrategy {
        private String cardNumber;

        public CreditCardPayment(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        @Override
        public void pay(double amount) {
            System.out.println("Paid $" + amount + " using Credit Card ending with " + cardNumber.substring(cardNumber.length() - 4));
        }
    }

    static class PayPalPayment implements PaymentStrategy {
        private String email;

        public PayPalPayment(String email) {
            this.email = email;
        }

        @Override
        public void pay(double amount) {
            System.out.println("Paid $" + amount + " using PayPal with email " + email);
        }
    }

    // Implement Context Class
    static class PaymentContext {
        private PaymentStrategy paymentStrategy;

        public PaymentContext(PaymentStrategy paymentStrategy) {
            this.paymentStrategy = paymentStrategy;
        }

        public void executePayment(double amount) {
            paymentStrategy.pay(amount);
        }
    }

    
    public static void main(String[] args) {
        // Create different payment strategies
        PaymentStrategy creditCard = new CreditCardPayment("1234567812345678");
        PaymentStrategy payPal = new PayPalPayment("user@example.com");

        // Create context with different strategies and execute payment
        PaymentContext paymentContext = new PaymentContext(creditCard);
        System.out.println("Testing Credit Card Payment:");
        paymentContext.executePayment(250.0);

        paymentContext = new PaymentContext(payPal);
        System.out.println("Testing PayPal Payment:");
        paymentContext.executePayment(150.0);
    }
}

//OUTPUT
// Testing Credit Card Payment:
// Paid $250.0 using Credit Card ending with 5678
// Testing PayPal Payment:
// Paid $150.0 using PayPal with email user@example.com