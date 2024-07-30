public class AdapterPattern {

    //  Define the Target Interface
    interface PaymentProcessor {
        void processPayment(double amount);
    }

    //  Implement Adaptee Classes
    // Example of a third-party payment gateway class with a different interface
    static class PayPalPaymentGateway {
        public void makePayment(double amount) {
            System.out.println("Processing payment of $" + amount + " through PayPal.");
        }
    }

    static class StripePaymentGateway {
        public void charge(double amount) {
            System.out.println("Charging $" + amount + " using Stripe.");
        }
    }

    //  Implement the Adapter Class
    // Adapter for PayPal
    static class PayPalAdapter implements PaymentProcessor {
        private PayPalPaymentGateway payPalPaymentGateway;

        public PayPalAdapter(PayPalPaymentGateway payPalPaymentGateway) {
            this.payPalPaymentGateway = payPalPaymentGateway;
        }

        @Override
        public void processPayment(double amount) {
            payPalPaymentGateway.makePayment(amount);
        }
    }

    // Adapter for Stripe
    static class StripeAdapter implements PaymentProcessor {
        private StripePaymentGateway stripePaymentGateway;

        public StripeAdapter(StripePaymentGateway stripePaymentGateway) {
            this.stripePaymentGateway = stripePaymentGateway;
        }

        @Override
        public void processPayment(double amount) {
            stripePaymentGateway.charge(amount);
        }
    }

    
    public static void main(String[] args) {
        // Create instances of third-party payment gateways
        PayPalPaymentGateway payPal = new PayPalPaymentGateway();
        StripePaymentGateway stripe = new StripePaymentGateway();

        // Create adapters for each payment gateway
        PaymentProcessor payPalAdapter = new PayPalAdapter(payPal);
        PaymentProcessor stripeAdapter = new StripeAdapter(stripe);

        // Use the adapters to process payments
        payPalAdapter.processPayment(100.0);
        stripeAdapter.processPayment(200.0);
    }
}

//OUTPUT
// Processing payment of $100.0 through PayPal.
// Charging $200.0 using Stripe.