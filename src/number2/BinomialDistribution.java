package number2;

import java.util.Arrays;

/**
 * Represents a specific Binomial Distribution defined by n (trials) and p (probability).
 * Calculates and stores the probabilities P(X=x) for x = 0 to n.
 */
public class BinomialDistribution {

    private final int n; // Number of trials
    private final double p; // Probability of success
    private final double[] probabilities; // Stores P(X=0), P(X=1), ..., P(X=n)

    /**
     * Creates a BinomialDistribution instance.
     *
     * @param n Number of trials (must be non-negative).
     * @param p Probability of success in a single trial (must be between 0 and 1).
     * @throws IllegalArgumentException if n < 0 or p is outside [0, 1].
     */
    public BinomialDistribution(int n, double p) {
        if (n < 0) {
            throw new IllegalArgumentException("Number of trials (n) cannot be negative.");
        }
        if (p < 0.0 || p > 1.0) {
            throw new IllegalArgumentException("Probability (p) must be between 0 and 1.");
        }
        this.n = n;
        this.p = p;
        this.probabilities = calculateAllProbabilities();
    }

    /**
     * Calculates P(X=x) for all possible values of x (0 to n).
     * @return An array where index `i` holds the value P(X=i).
     */
    private double[] calculateAllProbabilities() {
        double[] probs = new double[n + 1]; // Size n+1 for x=0 to x=n
        for (int k = 0; k <= n; k++) {
            // Use the static method from BinomialMath
            probs[k] = BinomialMath.calculateProbability(this.n, k, this.p);
        }
        return probs;
    }

    // --- Getters ---

    public int getN() {
        return n;
    }

    public double getP() {
        return p;
    }

    /**
     * Gets the probability P(X=k).
     * @param k The number of successes (0 <= k <= n).
     * @return The calculated probability P(X=k), or 0 if k is out of range.
     */
    public double getProbability(int k) {
        if (k < 0 || k > n) {
            return 0.0; // Index out of bounds
        }
        return probabilities[k];
    }

    /**
     * Gets an array containing all calculated probabilities P(X=x) for x=0 to n.
     * @return A defensive copy of the probabilities array.
     */
    public double[] getAllProbabilities() {
        // Return a copy to prevent external modification of the internal array
        return Arrays.copyOf(probabilities, probabilities.length);
    }

    /**
     * Calculates the sum of all probabilities (should be close to 1.0).
     * Useful for verification.
     * @return The sum of P(X=x) for x=0 to n.
     */
    public double getTotalProbabilitySum() {
        double sum = 0.0;
        for (double prob : probabilities) {
            sum += prob;
        }
        return sum;
    }
}