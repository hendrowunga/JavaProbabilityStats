package number2;

/**
 * Utility class containing static methods for binomial calculations.
 */
public final class BinomialMath { // final class - not meant to be subclassed

    // Private constructor to prevent instantiation of utility class
    private BinomialMath() {}

    /**
     * Calculates the binomial coefficient C(n, k) = n! / (k! * (n-k)!).
     * Uses a numerically stable method to avoid large intermediate factorial values.
     *
     * @param n Total number of trials (non-negative).
     * @param k Number of successes (0 <= k <= n).
     * @return The binomial coefficient C(n, k), or 0 if inputs are invalid.
     */
    public static double calculateCoefficient(int n, int k) {
        if (k < 0 || k > n || n < 0) {
            return 0; // Invalid input
        }
        if (k == 0 || k == n) {
            return 1.0; // C(n, 0) = 1, C(n, n) = 1
        }
        // Optimization: C(n, k) = C(n, n-k), calculate with the smaller k
        if (k > n / 2) {
            k = n - k;
        }

        // Calculation: (n * (n-1) * ... * (n-k+1)) / k!
        // Perform multiplication and division iteratively for stability
        double result = 1.0;
        for (int i = 1; i <= k; i++) {
            // result = result * (n - i + 1) / i; might suffer precision loss intermediate
            // Better: result = (result / i) * (n - i + 1); but careful with integer division if not double
            result *= (double)(n - i + 1) / i; // Ensure floating point division
        }
        return result;
    }

    /**
     * Calculates the binomial probability P(X=k) for given n, k, and p.
     * P(X=k) = C(n, k) * p^k * (1-p)^(n-k)
     *
     * @param n Total number of trials (non-negative).
     * @param k Number of successes (0 <= k <= n).
     * @param p Probability of success in a single trial (0 <= p <= 1).
     * @return The binomial probability P(X=k), or 0 if inputs are invalid.
     */
    public static double calculateProbability(int n, int k, double p) {
        if (k < 0 || k > n || n < 0 || p < 0.0 || p > 1.0) {
            return 0.0; // Invalid input
        }

        double coefficient = calculateCoefficient(n, k);
        if (coefficient == 0 && !(k==0 && n==0)) return 0.0; // Avoid unnecessary Math.pow if coeff is 0

        // Handle edge cases for Math.pow(0, 0) which can be problematic
        double probSuccessTerm;
        if (p == 0.0 && k == 0) probSuccessTerm = 1.0; // 0^0 is often taken as 1 in this context
        else if (p == 0.0) probSuccessTerm = 0.0; // 0^k is 0 for k>0
        else probSuccessTerm = Math.pow(p, k);

        double probFailureTerm;
        double q = 1.0 - p;
        int nMinusK = n - k;
        if (q == 0.0 && nMinusK == 0) probFailureTerm = 1.0; // (1-p)^0 = 1
        else if (q == 0.0) probFailureTerm = 0.0; // 0^(n-k) is 0 for n-k > 0
        else probFailureTerm = Math.pow(q, nMinusK);

        return coefficient * probSuccessTerm * probFailureTerm;
    }
}
