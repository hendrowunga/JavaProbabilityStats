package number1;

import java.text.DecimalFormat;

public class CalculatorRandomVariable {
    public static void main(String[] args) {
        // 1. Data Input (berdasarkan soal)
        double[] xValues = {1.0, 2.0, 3.0, 4.0, 5.0}; // Nilai-nilai X
        double[] pValues = {0.16, 0.22, 0.28, 0.20, 0.14}; // Probabilitas P(X)
        // Cek apakah jumlah probabilitas = 1 (opsional tapi bagus untuk validasi)
        double sumP = 0;
        for (double p : pValues) {
            sumP += p;
        }
        // Toleransi kecil untuk perbandingan floating point
        if (Math.abs(sumP - 1.0) > 1e-9) {
            System.out.println("Peringatan: Jumlah probabilitas tidak sama dengan 1 (Jumlah = " + sumP + ")");
            // Anda bisa memilih untuk menghentikan program di sini jika perlu
            // return;
        }
        // Format Angka Desimal
        DecimalFormat df2 = new DecimalFormat("0.00");   // 2 angka desimal
        DecimalFormat df4 = new DecimalFormat("0.0000"); // 4 angka desimal
        DecimalFormat df5 = new DecimalFormat("0.00000");// 5 angka desimal
        // 2. Menghitung Expected Value (E[X] atau μ)
        System.out.println("Menghitung Expected Value (Nilai Harapan):");
        System.out.println("----------------------------------------------");
        System.out.println(" x   | P(x)  | x * P(x)");
        System.out.println("-----|-------|----------");

        double expectedValue = 0.0;
        for (int i = 0; i < xValues.length; i++) {
            double x = xValues[i];
            double p = pValues[i];
            double xp = x * p;
            expectedValue += xp;
            System.out.println(
                    String.format(" %.0f", x) + "   | " +
                            df2.format(p) + "  | " +
                            String.format("%.0f(%.2f) = ", x, p) + df2.format(xp)
            );
        }


        System.out.println("----------------------------------------------");
        System.out.println(" Σ [x * P(x)] = " + df4.format(expectedValue)); // Tampilkan dengan 4 desimal seperti di tabel
        System.out.println("Expected Value E(X) = μ = " + df2.format(expectedValue)); // Hasil akhir biasanya 1 atau 2 desimal
        System.out.println("\n----------------------------------------------\n");


        // 3. Menghitung Variance (Var(X) atau σ²)
        // Rumus: Var(X) = Σ [(x - μ)² * P(x)]
        System.out.println("Menghitung Variance:");
        System.out.println("---------------------------------------------------------------------");
        System.out.println(" x   | P(x)  |  x - μ   |  (x - μ)² | (x - μ)² * P(x)");
        System.out.println("-----|-------|----------|-----------|-----------------");

        double variance = 0.0;
        double mu = expectedValue; // Gunakan nilai E(X) yang sudah dihitung

        for (int i = 0; i < xValues.length; i++) {
            double x = xValues[i];
            double p = pValues[i];
            double deviation = x - mu;                 // (x - μ)
            double deviationSq = deviation * deviation; // (x - μ)²
            double term = deviationSq * p;             // (x - μ)² * P(x)
            variance += term;

            System.out.println(
                    String.format(" %.0f", x) + "   | " +
                            df2.format(p) + "  | " +
                            String.format("% .2f", deviation) + "   | " + // % .2f memberi spasi untuk positif
                            df4.format(deviationSq) + "  | " +
                            // Menampilkan perkalian seperti di tabel output soal
                            // String.format("%.4f * %.2f = ", deviationSq, p) +
                            df5.format(term) // Tampilkan hasil term dengan 5 desimal seperti di tabel
            );
        }
        System.out.println("---------------------------------------------------------------------");
        System.out.println(" Σ [(x - μ)² * P(x)] = " + df4.format(variance)); // Hasil penjumlahan variance
        System.out.println("Variance Var(X) = σ² = " + df4.format(variance)); // Hasil akhir variance
        System.out.println("---------------------------------------------------------------------");

        // Opsional: Menghitung Standard Deviation (Simpangan Baku)
         double standardDeviation = Math.sqrt(variance);
         System.out.println("\nStandard Deviation σ = √" + df4.format(variance) + " = " + df2.format(standardDeviation));
    }
}
