public class DecoratorPattern {

    //  Define Component Interface
    interface Notifier {
        void send(String message);
    }

    //  Implement Concrete Component
    static class EmailNotifier implements Notifier {
        //Override
        public void send(String message) {
            System.out.println("Sending email with message: " + message);
        }
    }

    //  Implement Decorator Classes
    // Abstract decorator class
    static abstract class NotifierDecorator implements Notifier {
        protected Notifier decoratedNotifier;

        public NotifierDecorator(Notifier notifier) {
            this.decoratedNotifier = notifier;
        }

        //Override
        public void send(String message) {
            decoratedNotifier.send(message);
        }
    }

    // Concrete decorator for SMS
    static class SMSNotifierDecorator extends NotifierDecorator {
        public SMSNotifierDecorator(Notifier notifier) {
            super(notifier);
        }

        //Override
        public void send(String message) {
            super.send(message);
            System.out.println("Sending SMS with message: " + message);
        }
    }

    // Concrete decorator for Slack
    static class SlackNotifierDecorator extends NotifierDecorator {
        public SlackNotifierDecorator(Notifier notifier) {
            super(notifier);
        }

        //Override
        public void send(String message) {
            super.send(message);
            System.out.println("Sending Slack message with message: " + message);
        }
    }

    
    public static void main(String[] args) {
        // Create an email notifier
        Notifier emailNotifier = new EmailNotifier();

        // Decorate it with SMS and Slack
        Notifier smsAndSlackNotifier = new SlackNotifierDecorator(new SMSNotifierDecorator(emailNotifier));

        // Send a notification
        smsAndSlackNotifier.send("Hello, this is a test notification!");
    }
}

//OUTPUT
// Sending email with message: Hello, this is a test notification!
// Sending SMS with message: Hello, this is a test notification!
// Sending Slack message with message: Hello, this is a test notification!