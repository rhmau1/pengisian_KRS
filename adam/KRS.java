package adam;

import java.util.Scanner;

public class KRS {
    static int a = 0; // Indeks untuk data mahasiswa
    static final int data = 100000000; // Ukuran maksimum array
    static String[][] krs = new String[data][5]; // Array untuk menyimpan data mahasiswa
    static String[][] sksData = new String[data][2]; // Array untuk menyimpan NIM dan jumlah SKS
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        tambah();
        show();
    }

    public static void tambah() {
        String next;
        do {
            System.out.print("Masukkan NIM: ");
            krs[a][0] = sc.nextLine();

            System.out.print("Masukkan Nama: ");
            krs[a][1] = sc.nextLine();

            System.out.print("Masukkan Kode Mata Kuliah: ");
            krs[a][2] = sc.nextLine();

            System.out.print("Masukkan Nama Mata Kuliah: ");
            krs[a][3] = sc.nextLine();

            // Validasi jumlah SKS
            while (true) {
                System.out.print("Masukkan Jumlah SKS (1-3): ");
                krs[a][4] = sc.nextLine();
                int sks = Integer.parseInt(krs[a][4]);
                if (sks >= 1 && sks <= 3) {
                    break; // Jika valid, keluar dari loop
                } else {
                    System.out.println("Jumlah SKS harus antara 1 dan 3. Silakan input kembali.");
                }
            }

            // Update jumlah SKS untuk NIM
            String nim = krs[a][0];
            int sks = Integer.parseInt(krs[a][4]);

            // Cek apakah NIM sudah ada dalam sksData
            boolean nimExist = false;
            for (int i = 0; i <= a; i++) {
                if (sksData[i][0] != null && sksData[i][0].equals(nim)) {
                    // Jika NIM ditemukan, update jumlah SKS
                    int totalSKS = Integer.parseInt(sksData[i][1]) + sks;
                    sksData[i][1] = String.valueOf(totalSKS);
                    nimExist = true;
                    break;
                }
            }

            // Jika NIM belum ada, tambahkan data baru
            if (!nimExist) {
                sksData[a][0] = nim;
                sksData[a][1] = String.valueOf(sks);
            }

            // Cek total jumlah SKS
            int totalSKS = 0;
            if (sksData[a][1] != null) {
                totalSKS = Integer.parseInt(sksData[a][1]);
            }

            // Jika jumlah SKS lebih dari 24, batalkan input terakhir
            if (totalSKS > 24) {
                System.out.println("Jumlah SKS melebihi batas maksimum 24. Input terakhir dibatalkan.");
                for (int j = 0; j < krs[0].length; j++) {
                    krs[a][j] = null; // Kosongkan data input terakhir
                }
                sksData[a][1] = "0"; // Reset jumlah SKS untuk NIM ini
            }

            System.out.print("Apakah ingin menambahkan data lagi? (y/n): ");
            next = sc.nextLine();

            if (next.equalsIgnoreCase("y")) {
                a++; // Inkrementasi indeks untuk data baru
            }
        } while (next.equalsIgnoreCase("y"));
    }

    public static void show() {
        System.out.println("Data KRS:");
        for (int i = 0; i < a; i++) {
            System.out.println("NIM: " + krs[i][0] + ", Nama: " + krs[i][1] + ", Mata Kuliah: " + krs[i][3]
                    + ", Jumlah SKS: " + krs[i][4]);
        }
    }
}
