package number2;

import java.util.Scanner;
import java.text.DecimalFormat;

public class DistribusiBinomialKoin {

    // Fungsi untuk menghitung faktorial (menggunakan long untuk jangkauan lebih besar)
    public static long faktorial(int n) {
        if (n < 0) {
            // Faktorial tidak terdefinisi untuk negatif, tapi dalam konteks binomial tidak akan terjadi
            return -1; // Atau throw exception
        }
        if (n == 0) {
            return 1;
        }
        long hasil = 1;
        for (int i = 2; i <= n; i++) {
            hasil *= i;
        }
        return hasil;
    }

    // Fungsi untuk menghitung koefisien binomial C(n, k) = n! / (k! * (n-k)!)
    // Menggunakan double untuk hasil akhir karena pembagian bisa menghasilkan desimal
    // dan untuk menghindari overflow pada perkalian faktorial besar sebelum dibagi
    public static double koefisienBinomial(int n, int k) {
        if (k < 0 || k > n) {
            return 0; // Kombinasi tidak valid
        }
        if (k == 0 || k == n) {
            return 1; // C(n, 0) = 1, C(n, n) = 1
        }
        // Optimasi: C(n, k) = C(n, n-k), hitung dengan k yang lebih kecil
        if (k > n / 2) {
            k = n - k;
        }

        // Perhitungan yang lebih stabil untuk menghindari overflow besar
        // C(n, k) = (n * (n-1) * ... * (n-k+1)) / k!
        double hasil = 1.0;
        for (int i = 1; i <= k; i++) {
            // Lakukan perkalian dan pembagian secara bergantian
            hasil = hasil * (n - i + 1) / i;
        }
        return hasil;

        /*
         // Alternatif menggunakan faktorial (rawan overflow untuk n besar)
         long nFaktorial = faktorial(n);
         long kFaktorial = faktorial(k);
         long nMinKFaktorial = faktorial(n - k);

         if (kFaktorial <= 0 || nMinKFaktorial <= 0) { // Cek pembagi nol atau error faktorial
              System.err.println("Error perhitungan faktorial untuk C(" + n + ", " + k + ")");
              return -1; // Indikasi error
         }
         return (double) nFaktorial / (kFaktorial * nMinKFaktorial);
        */
    }

    // Fungsi untuk menghitung probabilitas binomial P(X=x)
    // n: jumlah percobaan (K lemparan)
    // k: jumlah sukses (jumlah head, x)
    // p: probabilitas sukses dalam satu percobaan (probabilitas head, 0.5)
    public static double probabilitasBinomial(int n, int k, double p) {
        double kombinasi = koefisienBinomial(n, k);
        if (kombinasi < 0) return -1; // Propagasi error jika ada

        double probSukses = Math.pow(p, k); // p^k
        double probGagal = Math.pow(1.0 - p, n - k); // (1-p)^(n-k)

        return kombinasi * probSukses * probGagal;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan jumlah pelemparan mata uang (K): ");
        int k = -1; // Inisialisasi dengan nilai tidak valid

        // Validasi input K harus non-negatif
        while (k < 0) {
            try {
                k = scanner.nextInt();
                if (k < 0) {
                    System.out.print("Jumlah pelemparan tidak boleh negatif. Masukkan lagi: ");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.print("Input tidak valid. Masukkan angka bulat non-negatif: ");
                scanner.next(); // Buang input yang salah
            }
        }

        // Probabilitas muncul muka (head) pada satu lemparan koin (diasumsikan adil)
        double pHead = 0.5;

        System.out.println("\nDistribusi Probabilitas Variabel Acak X (Jumlah Muka/Head)");
        System.out.println("Jumlah Pelemparan (K) = " + k);
        System.out.println("Probabilitas Head (p) = " + pHead);
        System.out.println("--------------------------------------------------");
        System.out.println(" X (Jumlah Head) |   P(X = x)   "); // Header tabel
        System.out.println("-----------------|--------------");

        DecimalFormat df = new DecimalFormat("0.000000"); // Format untuk probabilitas
        double totalProbabilitas = 0.0;

        // Loop untuk setiap kemungkinan nilai X (dari 0 sampai K)
        for (int x = 0; x <= k; x++) {
            double probabilitasX = probabilitasBinomial(k, x, pHead);
            if (probabilitasX >= 0) { // Cek jika tidak ada error perhitungan
                System.out.printf(" %-15d | %s%n", x, df.format(probabilitasX));
                totalProbabilitas += probabilitasX;
            } else {
                System.out.printf(" %-15d | Error Hitung%n", x);
            }
        }

        System.out.println("--------------------------------------------------");
        // Verifikasi: Jumlah semua probabilitas harus mendekati 1
        System.out.println("Total Probabilitas = " + df.format(totalProbabilitas));
        System.out.println("--------------------------------------------------");


        scanner.close(); // Tutup scanner setelah selesai digunakan
    }
}