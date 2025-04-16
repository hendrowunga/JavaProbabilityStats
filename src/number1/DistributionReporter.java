package number1;

import java.text.DecimalFormat;

/**
 * Handles the formatted printing of distribution calculations.
 */
public class DistributionReporter {

    private final DecimalFormat df2 = new DecimalFormat("0.00");   // 2 decimal places
    private final DecimalFormat df4 = new DecimalFormat("0.0000"); // 4 decimal places
    private final DecimalFormat df5 = new DecimalFormat("0.00000");// 5 decimal places
    private final DiscreteProbabilityDistribution distribution;

    public DistributionReporter(DiscreteProbabilityDistribution distribution) {
        if (distribution == null || !distribution.isValid()) {
            throw new IllegalArgumentException("Cannot create report for null or invalid distribution.");
        }
        this.distribution = distribution;
    }

    /**
     * Prints the detailed calculation steps for the Expected Value.
     */
    public void printExpectedValueCalculation() {
        System.out.println("Menghitung Expected Value (Nilai Harapan):");
        System.out.println("----------------------------------------------");
        System.out.println(" x   | P(x)  | x * P(x)");
        System.out.println("-----|-------|----------");

        double[] xValues = distribution.getXValues();
        double[] pValues = distribution.getPValues();
        double expectedValueSum = 0; // Recalculate sum for printing steps

        for (int i = 0; i < distribution.getSize(); i++) {
            double x = xValues[i];
            double p = pValues[i];
            double xp = x * p;
            expectedValueSum += xp; // Summing up for the final line
            System.out.println(
                    String.format(" %.0f", x) + "   | " +
                            df2.format(p) + "  | " +
                            String.format("%.0f(%.2f) = ", x, p) + df2.format(xp)
            );
        }
        System.out.println("----------------------------------------------");
        // Use the calculated value from distribution for final sum consistency
        System.out.println(" Σ [x * P(x)] = " + df4.format(distribution.getExpectedValue()));
        System.out.println("Expected Value E(X) = μ = " + df2.format(distribution.getExpectedValue()));
        System.out.println("\n----------------------------------------------\n");
    }

    /**
     * Prints the detailed calculation steps for the Variance.
     */
    public void printVarianceCalculation() {
        System.out.println("Menghitung Variance:");
        System.out.println("---------------------------------------------------------------------");
        System.out.println(" x   | P(x)  |  x - μ   |  (x - μ)² | (x - μ)² * P(x)");
        System.out.println("-----|-------|----------|-----------|-----------------");

        double[] xValues = distribution.getXValues();
        double[] pValues = distribution.getPValues();
        double mu = distribution.getExpectedValue(); // Get the calculated E(X)
        double varianceSum = 0; // Recalculate sum for printing steps

        for (int i = 0; i < distribution.getSize(); i++) {
            double x = xValues[i];
            double p = pValues[i];
            double deviation = x - mu;                 // (x - μ)
            double deviationSq = deviation * deviation; // (x - μ)²
            double term = deviationSq * p;             // (x - μ)² * P(x)
            varianceSum += term; // Summing up for the final line

            System.out.println(
                    String.format(" %.0f", x) + "   | " +
                            df2.format(p) + "  | " +
                            String.format("% .2f", deviation) + "   | " + // % .2f adds space for positive sign
                            df4.format(deviationSq) + "  | " +
                            df5.format(term)
            );
        }
        System.out.println("---------------------------------------------------------------------");
        // Use the calculated value from distribution for final sum consistency
        System.out.println(" Σ [(x - μ)² * P(x)] = " + df4.format(distribution.getVariance()));
        System.out.println("Variance Var(X) = σ² = " + df4.format(distribution.getVariance()));
        System.out.println("---------------------------------------------------------------------");
    }

    /**
     * Prints the calculated Standard Deviation.
     */
    public void printStandardDeviation() {
        double varianceValue = distribution.getVariance(); // Ensure variance is calculated
        double stdDevValue = distribution.getStandardDeviation();
        System.out.println("\nStandard Deviation σ = √" + df4.format(varianceValue) + " = " + df2.format(stdDevValue));
        System.out.println("---------------------------------------------------------------------");
    }

    /**
     * Prints all calculation reports.
     */
    public void printFullReport() {
        printExpectedValueCalculation();
        printVarianceCalculation();
        printStandardDeviation();
    }
}
