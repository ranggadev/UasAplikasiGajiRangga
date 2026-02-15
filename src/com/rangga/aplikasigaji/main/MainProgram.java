package com.rangga.aplikasigaji.main;

import com.rangga.aplikasigaji.model.*;
import com.rangga.aplikasigaji.interfaces.GajiPegawai;
import java.util.Scanner;

/**
 * Aplikasi Gaji menggunakan semua konsep OOP:
 * - Interface
 * - Inheritance
 * - Polymorphism
 * - Encapsulation
 * - Package
 */
public class MainProgram {
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("====================================================");
        System.out.println("           SELAMAT DATANG DI APLIKASI GAJI          ");
        System.out.println("====================================================");
        
        // Menu interaktif
        boolean running = true;
        while (running) {
            System.out.println("\n----------------------------------------------------");
            System.out.println("                      MENU UTAMA                      ");
            System.out.println("----------------------------------------------------");
            System.out.println("  1. Hitung Gaji Supervisor");
            System.out.println("  2. Hitung Gaji HRD");
            System.out.println("  3. Hitung Gaji Teknisi");
            System.out.println("  4. Hitung Gaji Karyawan");
            System.out.println("  5. Lihat Gaji Beberapa Pegawai");
            System.out.println("  0. Keluar");
            System.out.println("----------------------------------------------------");
            System.out.print("Pilih menu: ");
            
            int pilihan = scanner.nextInt();
            scanner.nextLine();
            
            switch (pilihan) {
                case 1:
                    hitungGajiPegawai("Supervisor");
                    break;
                case 2:
                    hitungGajiPegawai("HRD");
                    break;
                case 3:
                    hitungGajiPegawai("Teknisi");
                    break;
                case 4:
                    hitungGajiPegawai("Karyawan");
                    break;
                case 5:
                    testPolymorphism();
                    break;
                case 0:
                    running = false;
                    System.out.println("\nTerima kasih telah menggunakan sistem penggajian!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
        
        scanner.close();
    }
    
    /**
     * Demonstrasi Polymorphism
     * Menggunakan array Interface untuk menyimpan berbagai jenis pegawai
     */
    private static void testPolymorphism() {
        System.out.println("\n====================================================");
        System.out.println("             Lihat Gaji Beberapa Pegawai            ");
        System.out.println("       (Demonstrasi Polymorphism & Interface)       ");
        System.out.println("====================================================");

        System.out.println("\nKeterangan : Semua pegawai masuk jam 7, pulang jam 18");
        System.out.println("Status: Menikah dengan 2 anak\n");
        
        // Array dengan tipe Interface - menunjukkan polymorphism
        GajiPegawai[] daftarPegawai = {
            new Supervisor("Rangga Saputra"),
            new HRD("Saputra Jaya"),
            new Teknisi("Candra Pratama"),
            new Karyawan("Dewi Lestari")
        };
        
        // Polymorphism: memanggil method yang sama pada objek berbeda
        for (GajiPegawai pegawai : daftarPegawai) {
            if (pegawai instanceof Pegawai) {
                Pegawai p = (Pegawai) pegawai;
                System.out.println(p.getClass().getSimpleName() + " - " + p.getNama());
                System.out.printf("Total Gaji: Rp %,.0f\n", 
                    pegawai.hitungTotalGaji(7, 18, true, 2));
                System.out.println("-----------------------------------");
            }
        }
    }
    
    /**
     * Method untuk menghitung gaji pegawai secara interaktif
     */
    private static void hitungGajiPegawai(String jenisPegawai) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("INPUT DATA PEGAWAI - " + jenisPegawai.toUpperCase());
        System.out.println("=".repeat(60));
        
        System.out.print("Nama Pegawai: ");
        String nama = scanner.nextLine();
        
        System.out.print("Jam Masuk (1-24): ");
        int jamMasuk = scanner.nextInt();
        
        System.out.print("Jam Keluar (1-24): ");
        int jamKeluar = scanner.nextInt();
        
        System.out.print("Status Menikah (true/false): ");
        boolean menikah = scanner.nextBoolean();
        
        System.out.print("Jumlah Anak: ");
        int jumlahAnak = scanner.nextInt();
        scanner.nextLine();
        
        // Validasi input
        if (jamMasuk < 1 || jamMasuk > 24 || jamKeluar < 1 || jamKeluar > 24) {
            System.out.println("\nError: Jam harus antara 1-24!");
            return;
        }
        
        // Polymorphism: membuat objek berdasarkan pilihan
        Pegawai pegawai = null;
        switch (jenisPegawai) {
            case "Supervisor":
                pegawai = new Supervisor(nama);
                break;
            case "HRD":
                pegawai = new HRD(nama);
                break;
            case "Teknisi":
                pegawai = new Teknisi(nama);
                break;
            case "Karyawan":
                pegawai = new Karyawan(nama);
                break;
        }
        
        // Menampilkan slip gaji
        if (pegawai != null) {
            pegawai.displayInfo(jamMasuk, jamKeluar, menikah, jumlahAnak);
        }
    }
}
