package number2;

import java.text.DecimalFormat;

/**
 * Responsible for printing the details of a BinomialDistribution.
 */
public class BinomialReporter {

    private final BinomialDistribution distribution;
    private final DecimalFormat df = new DecimalFormat("0.000000"); // Format for probabilities

    public BinomialReporter(BinomialDistribution distribution) {
        if (distribution == null) {
            throw new IllegalArgumentException("Distribution cannot be null for reporter.");
        }
        this.distribution = distribution;
    }

    /**
     * Prints the distribution table (X vs P(X=x)).
     */
    public void printDistributionTable() {
        System.out.println("\nDistribusi Probabilitas Variabel Acak X (Jumlah Muka/Head)");
        System.out.println("Jumlah Pelemparan (n) = " + distribution.getN());
        System.out.println("Probabilitas Head (p) = " + distribution.getP());
        System.out.println("--------------------------------------------------");
        System.out.println(" X (Jumlah Head) |   P(X = x)   "); // Header tabel
        System.out.println("-----------------|--------------");

        double[] probabilities = distribution.getAllProbabilities(); // Get the probabilities
        for (int x = 0; x <= distribution.getN(); x++) {
            // String.format aligns columns better than printf in some terminals
            System.out.printf(" %-15d | %s%n", x, df.format(probabilities[x]));
        }

        System.out.println("--------------------------------------------------");
        // Verification: Print the sum of probabilities
        System.out.println("Total Probabilitas = " + df.format(distribution.getTotalProbabilitySum()));
        System.out.println("--------------------------------------------------");
    }
}