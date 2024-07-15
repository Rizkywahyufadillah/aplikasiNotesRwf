/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.rwfcompany.aplikasinotes;

import java.util.Scanner;

/**
 *
 * @author rizky
 */
public class AplikasiNotes {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int menu;

        DataStorage databaseStorage = new DatabaseStorage("database.db");

        do {
            System.out.println("");
            System.out.println("+++++++++Note App++++++++++");
            System.out.println("Oleh : Rizky wahyu fadillah");
            System.out.println("NIM  : 23201050");
            System.out.println("+++++++++++++++++++++++++++");
            System.out.println("1. Tambah Catatan");
            System.out.println("2. Tampilkan Catatan");
            System.out.println("3. Hapus Catatan");
            System.out.println("4. Keluar");
            System.out.print("Masukkan Pilihan Anda (1-4): ");

            while (!scanner.hasNextInt()) {
                System.out.println("Input harus berupa angka.");
                System.out.print("Masukkan Pilihan Anda (1-4): ");
                scanner.next();
            }
            menu = scanner.nextInt();

            if (menu < 1 || menu > 4) {
                System.out.println("Pilihan tidak valid. Silakan pilih 1-4.");
                continue;
            }

            System.out.println("============================");
            if (menu == 1) {
                System.out.print("Masukkan catatan Anda: ");
                scanner.nextLine();
                String teks = scanner.nextLine();
                databaseStorage.writeData(teks);
                System.out.println("Catatan berhasil disimpan : '" + teks + "'");
            } else if (menu == 2) {
                System.out.println("Menampilkan Catatan:");
                String data = databaseStorage.readData();
                if (data.isEmpty()) {
                    System.out.println("Tidak ada catatan yang tersimpan.");
                } else {
                    System.out.println("Isi catatan:");
                    System.out.println(data);
                }
            } else if (menu == 3) {
                System.out.print("Masukkan nomor catatan yang ingin dihapus: ");

                while (!scanner.hasNextInt()) {
                    System.out.println("Input harus berupa angka.");
                    System.out.print("Masukkan nomor catatan yang ingin dihapus: ");
                    scanner.next();
                }

                int idxCatatan = scanner.nextInt();

                databaseStorage.deleteData(idxCatatan);
                System.out.println("Catatan berhasil dihapus.");
            } else if (menu == 4) {
                System.out.println("Terimakasih, Telah Menggunakan Aplikasi Ini");
            }

        } while (menu != 4);
    }
}
