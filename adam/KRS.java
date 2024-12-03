package adam;

import java.util.Scanner;

public class KRS {
    static String[][] krs = new String[0][5]; // Array untuk menyimpan data mahasiswa
    static int[][] jumlahsks = new int[10000][2]; // Array untuk menyimpan NIM dan jumlah SKS
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println();
            System.out.println("=== Sistem Pemantauan KRS Mahasiswa ===");
            System.out.println("1. Tambah Data KRS");
            System.out.println("2. Tampil Daftar KRS Mahasiswa");
            System.out.println("3. Analisis Data KRS");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            int pilih = sc.nextInt();
            sc.nextLine(); // Menghilangkan newline setelah input angka

            switch (pilih) {
                case 1:
                    System.out.println();
                    System.out.println("--- Tambah Data KRS ---");
                    tambah();
                    break;
                case 2:
                    System.out.println();
                    System.out.println("--- Tampilkan Daftar KRS Mahasiswa ---");
                    System.out.print("Masukkan NIM yang dicari: ");
                    String nim = sc.nextLine();
                    print(nim);
                    break;
                case 3:
                    System.out.println();
                    System.out.println("--- Analisis Data KRS ---");
                    // Tambahkan logika analisis di sini jika diperlukan
                    break;
                case 4:
                    System.out.println("Terima Kasih!");
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
                    break;
            }
        }
    }

    public static void pindah() {
        // Jika array kosong, langsung inisialisasi baru dengan 1 baris
        if (krs.length == 0) {
            krs = new String[1][5];
            return;
        }

        // Jika array tidak kosong, pindahkan data ke array baru
        String[][] temp = new String[krs.length][krs[0].length];
        for (int i = 0; i < krs.length; i++) {
            for (int j = 0; j < krs[0].length; j++) {
                temp[i][j] = krs[i][j];
            }
        }

        // Perbesar ukuran array
        krs = new String[krs.length + 1][5];

        // Salin kembali data dari array sementara
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                krs[i][j] = temp[i][j];
            }
        }
    }

    public static void cekNIM(String nim) {
        boolean nimAda = false;
        for (int i = 0; i < jumlahsks.length; i++) {
            if (jumlahsks[i][0] == Integer.parseInt(nim)) {
                nimAda = true;
                break;
            }
        }
        if (!nimAda) {
            for (int j = 0; j < jumlahsks.length; j++) {
                if (jumlahsks[j][0] == 0) {
                    jumlahsks[j][0] = Integer.parseInt(nim);
                    jumlahsks[j][1] = 0;
                    break;
                }
            }
        }
    }

    public static void skstotal(String nim, String sks) {
        int totalSKS = 0;

        // Cari total SKS mahasiswa
        for (int i = 0; i < jumlahsks.length; i++) {
            if (jumlahsks[i][0] == Integer.parseInt(nim)) {
                totalSKS = jumlahsks[i][1];
                break;
            }
        }

        // Periksa apakah SKS baru melampaui batas
        if (totalSKS + Integer.parseInt(sks) > 24) {
            System.out.println("Total SKS melebihi batas 24. Data tidak ditambahkan.");
            return;
        }

        // Tambahkan SKS ke jumlahsks
        for (int i = 0; i < jumlahsks.length; i++) {
            if (jumlahsks[i][0] == Integer.parseInt(nim)) {
                jumlahsks[i][1] += Integer.parseInt(sks);
                break;
            }
        }
    }

    public static void tambah() {
        String next;
        do {
            System.out.print("Masukkan Nama: ");
            String nama = sc.nextLine();

            System.out.print("Masukkan NIM: ");
            String nim = sc.nextLine();

            System.out.print("Masukkan Kode Mata Kuliah: ");
            String kode = sc.nextLine();

            System.out.print("Masukkan Nama Mata Kuliah: ");
            String matkul = sc.nextLine();

            String sks;
            while (true) {
                System.out.print("Masukkan Jumlah SKS (1-3): ");
                sks = sc.nextLine();
                if (Integer.parseInt(sks) >= 1 && Integer.parseInt(sks) <= 3) {
                    break;
                } else {
                    System.out.println("Jumlah SKS harus antara 1 dan 3.");
                }
            }

            // Pindahkan data ke array baru
            pindah();

            krs[krs.length - 1][0] = nama;
            krs[krs.length - 1][1] = nim;
            cekNIM(nim);
            krs[krs.length - 1][2] = kode;
            krs[krs.length - 1][3] = matkul;
            skstotal(nim, sks);
            krs[krs.length - 1][4] = sks;

            System.out.print("Ingin menambahkan data lagi? (y/n): ");
            next = sc.nextLine();
            if (next.equalsIgnoreCase("n")) {
                break;
            }

        } while (next.equalsIgnoreCase("y"));
    }

    public static void print(String nim) {
        int totalSKS = 0;
    
        if (krs.length == 0) {
            System.out.println("Data KRS kosong.");
            return;
        }
    
        System.out.println("Daftar KRS: ");
        System.out.println("NIM \t\tNama \t\tKode MK \tNama Matkul \t\tSKS");
    
        for (int i = 0; i < krs.length; i++) {
            if (krs[i][1].equalsIgnoreCase(nim)) {
                for (int j = 0; j < krs[0].length; j++) {
                    if (j == 4) { // Kolom SKS
                        System.out.print(krs[i][j] + "\t\t");
                        totalSKS += Integer.parseInt(krs[i][j]);
                    } else if (j == 2) { // Kolom Kode MK
                        System.out.print(krs[i][j] + "\t\t\t");
                    } else {
                        System.out.print(krs[i][j] + "\t\t");
                    }
                }
                System.out.println();
            }
        }
        System.out.println("Total SKS: " + totalSKS);
    }
    
}
