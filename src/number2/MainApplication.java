package number2;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main application class to get user input and display the binomial distribution.
 */
public class MainApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = -1; // Number of trials (K from user input)
        double p = 0.5; // Probability of success (Head for a fair coin)

        // --- Get User Input ---
        System.out.print("Masukkan jumlah pelemparan mata uang (K >= 0): ");
        while (n < 0) {
            try {
                n = scanner.nextInt();
                if (n < 0) {
                    System.out.print("Jumlah pelemparan tidak boleh negatif. Masukkan lagi: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Input tidak valid. Masukkan angka bulat non-negatif: ");
                scanner.next(); // Consume the invalid input
            }
        }

        try {
            // --- Create Objects ---
            // 1. Create the distribution object (handles calculation internally)
            BinomialDistribution coinDistribution = new BinomialDistribution(n, p);

            // 2. Create the reporter object
            BinomialReporter reporter = new BinomialReporter(coinDistribution);

            // --- Generate Report ---
            // 3. Tell the reporter to print the table
            reporter.printDistributionTable();

        } catch (IllegalArgumentException e) {
            // Catch errors from BinomialDistribution constructor if p was somehow invalid
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close(); // Always close the scanner
        }
    }
}