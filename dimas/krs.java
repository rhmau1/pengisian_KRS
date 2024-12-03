package dimas;
import java.util.Scanner;

public class krs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan jumlah mahasiswa: ");
        int jumlahMahasiswa = scanner.nextInt();

        int[][] jumlahsks = new int[jumlahMahasiswa][2];

        for (int i = 0; i < jumlahMahasiswa; i++) {
            System.out.print("Masukkan ID mahasiswa ke-" + (i + 1) + ": ");
            jumlahsks[i][0] = scanner.nextInt(); 
            System.out.print("Masukkan jumlah SKS mahasiswa ke-" + (i + 1) + ": ");
            jumlahsks[i][1] = scanner.nextInt(); 
        }

        int sksKurang = 0;

        for (int i = 0; i < jumlahsks.length; i++) {
            if (jumlahsks[i][1] < 20 && jumlahsks[i][1] > 0) {
                sksKurang++;
            } else if (jumlahsks[i][1] == 0) {
                sksKurang--;
                break;
            }
        }
        System.out.println("Jumlah mahasiswa yang SKS-nya kurang dari 20: " + sksKurang);

        scanner.close();
    }
}

