

public class Logger {

    // Private static instance of the class
    private static Logger instance;

    // Private constructor to prevent instantiation
    private Logger() {
        // Initialization code (if any)
    }

    // Public static method to provide access to the instance
    public static synchronized Logger getInstance() {
        // Create instance if it doesn't exist
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // Example method to demonstrate logging
    public void log(String message) {
        System.out.println("LOG: " + message);
    }

   
    public static void main(String[] args) {
        // Get the single instance of Logger
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        // Check if both references point to the same instance
        if (logger1 == logger2) {
            System.out.println("Both logger1 and logger2 are the same instance.");
        } else {
            System.out.println("Different instances of Logger.");
        }

        // Test the log method
        logger1.log("This is a test log message.");
        logger2.log("This message should also be logged by the same instance.");
    }
}

//OUTPUT
// Both logger1 and logger2 are the same instance.
// LOG: This is a test log message.
// LOG: This message should also be logged by the same instance.