package com.rangga.aplikasigaji.model;

import com.rangga.aplikasigaji.interfaces.GajiPegawai;

/**
 * Abstract Class Pegawai sebagai base class untuk semua jenis pegawai
 * Menerapkan konsep Abstraction, Encapsulation, dan Inheritance
 */
public abstract class Pegawai implements GajiPegawai {
    
    // Konstanta jam kerja normal
    protected static final int JAM_MASUK_NORMAL = 7;
    protected static final int JAM_KELUAR_NORMAL = 16;
    protected static final double POTONGAN_PER_JAM = 100000;
    
    // Attribute dengan encapsulation (private)
    private String nama;
    private String kode;
    
    // Komponen gaji (akan di-set oleh subclass)
    protected double gajiPokok;
    protected double transport;
    protected double lemburPerJam;
    protected double tunjanganIstri;
    protected double tunjanganSatuAnak;
    protected double tunjanganLebihDariSatuAnak;
    
    /**
     * Constructor
     * @param nama
     * @param kode
     */
    public Pegawai(String nama, String kode) {
        this.nama = nama;
        this.kode = kode;
    }
    
    // Getter dan Setter (Encapsulation)
    public String getNama() {
        return nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public String getKode() {
        return kode;
    }
    
    /**
     * Menghitung jam lembur
     * @param jamKeluar
     * @return
     */
    protected int hitungJamLembur(int jamKeluar) {
        if (jamKeluar > JAM_KELUAR_NORMAL) {
            return jamKeluar - JAM_KELUAR_NORMAL;
        }
        return 0;
    }
    
    /**
     * Menghitung potongan keterlambatan
     * @param jamMasuk
     * @return
     */
    protected double hitungPotonganTerlambat(int jamMasuk) {
        if (jamMasuk > JAM_MASUK_NORMAL) {
            int jamTerlambat = jamMasuk - JAM_MASUK_NORMAL;
            return POTONGAN_PER_JAM * jamTerlambat;
        }
        return 0;
    }
    
    /**
     * Menghitung potongan pulang cepat
     * @param jamKeluar
     * @return
     */
    protected double hitungPotonganPulangCepat(int jamKeluar) {
        if (jamKeluar < JAM_KELUAR_NORMAL) {
            int jamKekurangan = JAM_KELUAR_NORMAL - jamKeluar;
            return POTONGAN_PER_JAM * jamKekurangan;
        }
        return 0;
    }
    
    /**
     * Implementasi method dari interface
     * Menghitung total potongan (terlambat + pulang cepat)
     */
    @Override
    public double hitungPotongan(int jamMasuk, int jamKeluar) {
        double potonganTerlambat = hitungPotonganTerlambat(jamMasuk);
        double potonganPulangCepat = hitungPotonganPulangCepat(jamKeluar);
        return potonganTerlambat + potonganPulangCepat;
    }
    
    /**
     * Implementasi method dari interface
     * Menghitung total gaji dengan semua komponen
     */
    @Override
    public double hitungTotalGaji(int jamMasuk, int jamKeluar, boolean statusMenikah, int jumlahAnak) {
        double gaji = hitungGajiPokok();
        double transportasi = hitungTransport();
        double lembur = hitungLembur(jamKeluar);
        double tunjanganKeluarga = hitungTunjanganKeluarga(statusMenikah, jumlahAnak);
        double potongan = hitungPotongan(jamMasuk, jamKeluar);
        
        return gaji + transportasi + lembur + tunjanganKeluarga - potongan;
    }
    
    /**
     * Method untuk menampilkan informasi gaji pegawai
     */
    public void displayInfo(int jamMasuk, int jamKeluar, boolean statusMenikah, int jumlahAnak) {
        System.out.println("\n" + "-----------------------------------");
        System.out.println("SLIP GAJI PEGAWAI");
        System.out.println("-----------------------------------");
        System.out.println("Nama           : " + nama);
        System.out.println("Jabatan        : " + getClass().getSimpleName());
        System.out.println("Kode           : " + kode);
        System.out.println("-----------------------------------");
        System.out.println("Jam Masuk      : " + jamMasuk + ":00");
        System.out.println("Jam Keluar     : " + jamKeluar + ":00");
        System.out.println("Status Menikah : " + (statusMenikah ? "Ya" : "Tidak"));
        System.out.println("Jumlah Anak    : " + jumlahAnak);
        System.out.println("-----------------------------------");
        System.out.println("KOMPONEN GAJI:");
        System.out.printf("  Gaji Pokok              : Rp %,.0f\n", hitungGajiPokok());
        System.out.printf("  Transport               : Rp %,.0f\n", hitungTransport());
        System.out.printf("  Lembur (%d jam)          : Rp %,.0f\n", 
                         hitungJamLembur(jamKeluar), hitungLembur(jamKeluar));
        System.out.printf("  Tunjangan Keluarga      : Rp %,.0f\n", 
                         hitungTunjanganKeluarga(statusMenikah, jumlahAnak));
        System.out.println("-----------------------------------");
        System.out.println("POTONGAN:");
        
        double potonganTerlambat = hitungPotonganTerlambat(jamMasuk);
        double potonganPulangCepat = hitungPotonganPulangCepat(jamKeluar);
        
        if (potonganTerlambat > 0) {
            int jamTerlambat = jamMasuk - JAM_MASUK_NORMAL;
            System.out.printf("  Terlambat (%d jam)       : Rp %,.0f\n", 
                             jamTerlambat, potonganTerlambat);
        }
        if (potonganPulangCepat > 0) {
            int jamKekurangan = JAM_KELUAR_NORMAL - jamKeluar;
            System.out.printf("  Pulang Cepat (%d jam)    : Rp %,.0f\n", 
                             jamKekurangan, potonganPulangCepat);
        }
        if (potonganTerlambat == 0 && potonganPulangCepat == 0) {
            System.out.println("  Tidak ada potongan");
        }
        
        System.out.printf("  Total Potongan          : Rp %,.0f\n", 
                         hitungPotongan(jamMasuk, jamKeluar));
        System.out.println("=".repeat(60));
        System.out.printf("TOTAL GAJI BERSIH         : Rp %,.0f\n", 
                         hitungTotalGaji(jamMasuk, jamKeluar, statusMenikah, jumlahAnak));
        System.out.println("=".repeat(60));
    }
}
