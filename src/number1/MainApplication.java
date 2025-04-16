package number1;

/**
 * Main class to demonstrate the calculation and reporting
 * of expected value and variance for a discrete random variable.
 */
public class MainApplication {

    public static void main(String[] args) {
        // 1. Define the Data
        double[] xData = {1.0, 2.0, 3.0, 4.0, 5.0}; // Nilai-nilai X
        double[] pData = {0.16, 0.22, 0.28, 0.20, 0.14}; // Probabilitas P(X)

        try {
            // 2. Create the Distribution Object
            // The constructor handles validation
            DiscreteProbabilityDistribution distribution =
                    new DiscreteProbabilityDistribution(xData, pData);

            // 3. Create the Reporter Object
            DistributionReporter reporter = new DistributionReporter(distribution);

            // 4. Print the Reports
            reporter.printFullReport();

            // You can also access individual calculated values if needed:
            // System.out.println("\n--- Calculated Values ---");
            // System.out.println("E[X] = " + distribution.getExpectedValue());
            // System.out.println("Var(X) = " + distribution.getVariance());
            // System.out.println("StdDev(X) = " + distribution.getStandardDeviation());

        } catch (IllegalArgumentException e) {
            System.err.println("Error creating or processing distribution: " + e.getMessage());
            // Handle the error appropriately, maybe exit
            System.exit(1);
        }
    }
}
