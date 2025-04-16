package number1;

import java.util.Arrays;

public class DiscreteProbabilityDistribution {
    private final double[] xValues;
    private final double[] pValues;
    private double expectedValue = Double.NaN;
    private double variance = Double.NaN;
    private boolean isValid = false;

    /**
     * Constructor for DiscreteProbabilityDistribution.
     * Validates the input data.
     *
     * @param xValues Array of possible values for the random variable X.
     * @param pValues Array of corresponding probabilities P(X=x). Must sum to 1.
     * @throws IllegalArgumentException if input arrays are null, have different lengths,
     *                                  or if probabilities don't sum close to 1.
     */
    public DiscreteProbabilityDistribution(double[] xValues, double[] pValues) {
        validateInputs(xValues, pValues);
        this.xValues = Arrays.copyOf(xValues, xValues.length); // Defensive copy
        this.pValues = Arrays.copyOf(pValues, pValues.length); // Defensive copy
        this.isValid = true; // Validation passed
    }

    /**
     * Validates the input arrays for the distribution.
     */
    private void validateInputs(double[] x, double[] p) {
        if (x == null || p == null) {
            throw new IllegalArgumentException("Input arrays cannot be null.");
        }
        if (x.length != p.length) {
            throw new IllegalArgumentException("xValues and pValues arrays must have the same length.");
        }
        if (x.length == 0) {
            throw new IllegalArgumentException("Input arrays cannot be empty.");
        }

        double sumP = 0;
        for (double prob : p) {
            if (prob < 0 || prob > 1) {
                throw new IllegalArgumentException("Probabilities must be between 0 and 1 (inclusive). Found: " + prob);
            }
            sumP += prob;
        }

        // Check if the sum of probabilities is close to 1
        double tolerance = 1e-9; // Tolerance for floating-point comparison
        if (Math.abs(sumP - 1.0) > tolerance) {
            // It's often better to throw an exception for invalid data
            throw new IllegalArgumentException("Probabilities do not sum to 1 (Sum = " + sumP + ")");
            // Or, if you want a warning instead:
            // System.err.println("Warning: Probabilities do not sum close to 1 (Sum = " + sumP + ")");
        }
    }
    /**
     * Calculates and returns the expected value (μ).
     * Caches the result after the first calculation.
     *
     * @return The expected value E[X].
     */
    public double getExpectedValue() {
        if (Double.isNaN(this.expectedValue)) { // Calculate only if not already calculated
            double sumXp = 0.0;
            for (int i = 0; i < xValues.length; i++) {
                sumXp += xValues[i] * pValues[i];
            }
            this.expectedValue = sumXp;
        }
        return this.expectedValue;
    }

    /**
     * Calculates and returns the variance (σ²).
     * Requires the expected value to be calculated first.
     * Caches the result after the first calculation.
     *
     * @return The variance Var(X).
     */
    public double getVariance() {
        if (Double.isNaN(this.variance)) { // Calculate only if not already calculated
            double mu = getExpectedValue(); // Ensure expected value is calculated
            double sumVarianceTerm = 0.0;
            for (int i = 0; i < xValues.length; i++) {
                double deviation = xValues[i] - mu;
                sumVarianceTerm += deviation * deviation * pValues[i]; // (x - μ)² * P(x)
            }
            this.variance = sumVarianceTerm;
        }
        return this.variance;
    }

    /**
     * Calculates and returns the standard deviation (σ).
     *
     * @return The standard deviation (sqrt of variance).
     */
    public double getStandardDeviation() {
        return Math.sqrt(getVariance()); // Ensure variance is calculated
    }

    // --- Getters for accessing internal data (needed by the reporter) ---

    public double[] getXValues() {
        return Arrays.copyOf(xValues, xValues.length); // Return a copy
    }

    public double[] getPValues() {
        return Arrays.copyOf(pValues, pValues.length); // Return a copy
    }

    public int getSize() {
        return xValues.length;
    }

    public boolean isValid() {
        return isValid;
    }

}
