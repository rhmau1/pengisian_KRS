package fijri;

import java.util.Scanner;

public class krs {

    static String[][] KRS = new String[0][5]; // Array kosong untuk memulai

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("===Sistem Pemantauan KRS Mahasiswa===");
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
                    input(sc);
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
                    break;
                case 4:
                    System.out.println("Terima Kasih!");
                    return; // Keluar dari program
                default:
                    System.out.println("Pilihan tidak valid!");
                    break;
            }
        }
    }

    public static void input(Scanner sc) {
        System.out.print("Nama mahasiswa: ");
        String nama = sc.nextLine();
        System.out.print("NIM: ");
        String nim = sc.nextLine();

        char next;
        int currentTotalSKS = totalSKS(nim); // Menghitung total SKS awal

        do {
            if (currentTotalSKS >= 24) {
                System.out.println("Jumlah SKS sudah mencapai batas maksimum 24.");
                break;
            }

            // Salin isi array KRS lama ke array temp
            String[][] temp = new String[KRS.length][5];
            for (int i = 0; i < KRS.length; i++) {
                for (int j = 0; j < KRS[i].length; j++) {
                    temp[i][j] = KRS[i][j]; // Menyalin data dari KRS lama ke temp
                }
            }

            // Perbesar ukuran array KRS
            KRS = new String[temp.length + 1][5];

            // Salin kembali isi array temp ke array KRS yang baru
            for (int i = 0; i < temp.length; i++) {
                for (int j = 0; j < temp[i].length; j++) {
                    KRS[i][j] = temp[i][j]; // Menyalin kembali data ke KRS yang baru
                }
            }

            KRS[KRS.length - 1][0] = nama;

            KRS[KRS.length - 1][1] = nim;

            System.out.print("Kode matkul: ");
            KRS[KRS.length - 1][2] = sc.nextLine();

            System.out.print("Nama matkul: ");
            KRS[KRS.length - 1][3] = sc.nextLine();

            while (true) {
                System.out.print("Jumlah SKS (1-3): ");
                String input = sc.nextLine();
                int sks = Integer.parseInt(input);
                if (sks >= 1 && sks <= 3) {
                    if (currentTotalSKS + sks <= 24) {
                        KRS[KRS.length - 1][4] = input; // Simpan SKS jika valid
                        System.out.println("Data mata kuliah berhasil ditambahkan.");
                        currentTotalSKS += sks; // Tambahkan ke total SKS
                        break;
                    } else {
                        System.out.println("Jumlah SKS total melebihi 24. Input tidak valid.");
                        // Hapus baris terakhir yang tidak valid dari array KRS
                        String[][] temp2 = new String[KRS.length - 1][5];
                        for (int i = 0; i < KRS.length - 1; i++) {
                            temp2[i] = KRS[i];
                        }
                        KRS = temp2; // Kembalikan array ke ukuran sebelumnya
                        break;
                    }
                } else {
                    System.out.println("Jumlah SKS harus antara 1 dan 3. Silakan input kembali.");
                }
            }

            if (currentTotalSKS >= 24) {
                System.out.println("Jumlah SKS sudah mencapai batas maksimum 24.");
                break;
            }

            System.out.print("Tambah matkul lain? (y/n): ");
            next = sc.next().charAt(0);
            sc.nextLine();

        } while (next == 'y' || next == 'Y');
        System.out.println("total SKS yang diambil: " + totalSKS(nim));
    }

    public static int totalSKS(String nim) {
        int totalSKS = 0;
        for (int i = 0; i < KRS.length; i++) {
            if (KRS[i][1].equalsIgnoreCase(nim)) {
                totalSKS += Integer.parseInt(KRS[i][4]);
            }
        }
        return totalSKS;
    }

    public static void print(String nim) {
        int totalSKS = 0;
        if (KRS.length == 0) {
            System.out.println("Data KRS kosong.");
            return;
        }
        System.out.println("Daftar KRS: ");

        System.out.print("NIM \t\t" + "nama \t" + "\t\t" + "kode MK \t" + "nama matkul \t\t" + "SKS \t");
        System.out.println();
        for (int i = 0; i < KRS.length; i++) {
            if (KRS[i][1].equalsIgnoreCase(nim)) {
                for (int j = 1; j >= 0; j--) {
                    System.out.print(KRS[i][j] + "\t");
                }
                System.out.print("\t\t");
                for (int j = 2; j < KRS[0].length; j++) {
                    if (j == 2) {
                        System.out.print(KRS[i][j] + "\t\t");
                    } else {
                        System.out.print(KRS[i][j] + "\t");
                    }
                }
                System.out.println();
                totalSKS += Integer.parseInt(KRS[i][4]);
            }
        }
        System.out.println("total SKS: " + totalSKS);
    }
}
