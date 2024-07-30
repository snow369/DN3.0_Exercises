public class Financial_Forecasting {

    // Recursive method to calculate future value
    public static double calculateFutureValue(double initialValue, double growthRate, int years) {
        if (years == 0) {
            return initialValue;
        }
        return calculateFutureValue(initialValue * (1 + growthRate), growthRate, years - 1);
    }

    // Iterative method to calculate future value
    public static double calculateFutureValueIterative(double initialValue, double growthRate, int years) {
        double futureValue = initialValue;
        for (int i = 0; i < years; i++) {
            futureValue *= (1 + growthRate);
        }
        return futureValue;
    }

    public static void main(String[] args) {
        double initialValue = 1000.0; // Starting value
        double growthRate = 0.05; // 5% growth rate
        int years = 10; // Number of years

        // Calculate future value using recursion
        double futureValueRecursive = calculateFutureValue(initialValue, growthRate, years);
        System.out.println("Future Value (Recursive) after " + years + " years: $" + futureValueRecursive);

        // Calculate future value using iteration
        double futureValueIterative = calculateFutureValueIterative(initialValue, growthRate, years);
        System.out.println("Future Value (Iterative) after " + years + " years: $" + futureValueIterative);
    }
}

//OUTPUT
// Future Value (Recursive) after 10 years: $1628.8946267774422
// Future Value (Iterative) after 10 years: $1628.8946267774422